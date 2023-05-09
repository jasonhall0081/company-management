package cenglisch.hiring.domain.model.candidate;

import cenglisch.domain.model.EventHandler;
import cenglisch.hiring.domain.model.candidate.exception.CandidateApplicationException;
import cenglisch.hiring.domain.model.candidate.exception.CandidateException;
import cenglisch.hiring.domain.model.candidate.exception.CandidateNotFoundException;
import cenglisch.hiring.domain.model.job.JobId;
import cenglisch.domain.model.PersonId;

import java.util.Optional;

@org.jmolecules.ddd.annotation.Service
public final class CandidateService {
    private final CandidateSecondaryPort candidateSecondaryPort;

    private final EventHandler eventHandler;

    public CandidateService(
            final CandidateSecondaryPort candidateSecondaryPort,
            final EventHandler eventHandler
    ) {
        this.candidateSecondaryPort = candidateSecondaryPort;
        this.eventHandler = eventHandler;
    }

    public Optional<Candidate> find(final CandidateId candidateId) {
        return candidateSecondaryPort.find(candidateId);
    }

    public boolean isCandidateInState(final CandidateId candidateId, final CandidateState candidateState) {
        final Candidate candidate = find(candidateId).orElseThrow(CandidateNotFoundException::new);
        return candidate.getCandidateState() == candidateState;
    }

    private boolean personAlreadyAppliedForJob(final PersonId personId, final JobId jobId) {
        return candidateSecondaryPort.existsByPersonIdAndJobId(personId, jobId);
    }

    public void newCandidate(final PersonId personId, final JobId jobId) {
        if (personAlreadyAppliedForJob(personId, jobId)) {
            throw new CandidateApplicationException("person has already applied for this job");
        }

        Candidate candidate = candidateSecondaryPort.save(new Candidate(personId, jobId));
        eventHandler.publish(
                CandidateStateEventFactory.make(
                        candidate.getCandidateId(),
                        candidate.getCandidateState(),
                        candidate.getJobId()
                )
        );
    }

    public void changeCandidateState(final CandidateId candidateId, final CandidateState candidateState) {
        if (candidateState == CandidateState.APPLICATION_ACCEPTED) {
            throw new CandidateException("invalid state change");
        }
        final Candidate candidate = find(candidateId).orElseThrow(CandidateNotFoundException::new);
        candidate.changeCandidateState(candidateState);
        candidateSecondaryPort.save(candidate);
        eventHandler.publish(
                CandidateStateEventFactory.make(candidateId, candidateState, candidate.getJobId())
        );
    }
}
