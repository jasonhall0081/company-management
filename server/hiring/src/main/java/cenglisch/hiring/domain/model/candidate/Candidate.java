package cenglisch.hiring.domain.model.candidate;

import cenglisch.Default;
import cenglisch.domain.model.PersonId;
import cenglisch.hiring.domain.model.Entity;
import cenglisch.hiring.domain.model.candidate.exception.CandidateException;
import cenglisch.hiring.domain.model.job.JobId;

@org.jmolecules.ddd.annotation.AggregateRoot
public final class Candidate extends Entity {
    private CandidateId candidateId;
    private PersonId personId;
    private CandidateState candidateState;
    private JobId jobId;

    public Candidate(final PersonId personId, final JobId jobId) {
        setPersonId(personId);
        setJobId(jobId);
        setCandidateState(CandidateState.APPLICATION_ACCEPTED);
    }

    @Default
    public Candidate(
            final CandidateId candidateId,
            final JobId jobId,
            final PersonId personId,
            final CandidateState candidateState
    ) {
        setCandidateId(candidateId);
        setJobId(jobId);
        setPersonId(personId);
        setCandidateState(candidateState);
    }

    public void changeCandidateState(final CandidateState stateChange) {
        if (candidateState == stateChange) {
            return;
        }
        boolean stateChangeValid = false;
        switch (candidateState) {
            case APPLICATION_ACCEPTED ->
                    stateChangeValid =
                            stateChange == CandidateState.APPLICATION_APPROVED
                            || stateChange == CandidateState.APPLICATION_REJECTED;
            case APPLICATION_APPROVED ->
                    stateChangeValid = stateChange == CandidateState.ADOPTED || stateChange == CandidateState.REJECTED;
            default -> stateChangeValid = false;
        }
        if (!stateChangeValid) {
            throw new CandidateException(
                    "Illegal State Change: " + candidateState.name() + " to " + stateChange.name() + " invalid"
            );
        }
        setCandidateState(stateChange);
    }

    public void setCandidateId(final CandidateId candidateId) {
        assertArgumentNotNull(candidateId, "candidate id cannot be null");
        this.candidateId = candidateId;
    }

    private void setPersonId(final PersonId personId) {
        assertArgumentNotNull(personId, "person id cannot be null");
        this.personId = personId;
    }

    private void setCandidateState(final CandidateState candidateState) {
        assertArgumentNotNull(candidateState, "candidate state cannot be null");
        this.candidateState = candidateState;
    }

    private void setJobId(final JobId jobId) {
        assertArgumentNotNull(jobId, "job id cannot be null");
        this.jobId = jobId;
    }

    public CandidateId getCandidateId() {
        return candidateId;
    }

    public PersonId getPersonId() {
        return personId;
    }

    public CandidateState getCandidateState() {
        return candidateState;
    }

    public JobId getJobId() {
        return jobId;
    }
}
