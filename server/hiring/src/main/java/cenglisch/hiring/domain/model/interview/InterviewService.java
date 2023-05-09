package cenglisch.hiring.domain.model.interview;

import cenglisch.domain.model.EventHandler;
import cenglisch.hiring.domain.model.candidate.CandidateId;
import cenglisch.hiring.domain.model.interview.exception.InterviewException;
import cenglisch.hiring.domain.model.interview.exception.InterviewNotFoundException;
import cenglisch.hiring.domain.model.interview.state.InterviewGenerated;
import cenglisch.hiring.domain.model.interview.state.InterviewState;
import cenglisch.hiring.domain.model.interview.state.InterviewStateEventFactory;
import cenglisch.hiring.domain.model.interview.type.InterviewHeldOffline;
import cenglisch.hiring.domain.model.interview.type.InterviewHeldOnline;
import cenglisch.hiring.domain.model.interview.type.InterviewType;

import java.util.Optional;

@org.jmolecules.ddd.annotation.Service
public final class InterviewService {

    private final InterviewSecondaryPort interviewSecondaryPort;
    private final EventHandler eventHandler;

    public InterviewService(
        final InterviewSecondaryPort interviewSecondaryPort,
        final EventHandler eventHandler
    ) {
        this.interviewSecondaryPort = interviewSecondaryPort;
        this.eventHandler = eventHandler;
    }

    public Optional<Interview> find(final InterviewId interviewId) {
        return interviewSecondaryPort.find(interviewId);
    }

    public Optional<Interview> findByCandidateId(final CandidateId candidateId) {
        return interviewSecondaryPort.findByCandidateId(candidateId);
    }

    public boolean isInterviewInState(final CandidateId candidateId, final InterviewState interviewState) {
        Interview interview = findByCandidateId(candidateId).orElseThrow(InterviewNotFoundException::new);
        return interview.getInterviewState() == interviewState;
    }

    public void newInterview(final CandidateId candidateId) {
        findByCandidateId(candidateId).ifPresent(s -> {
            throw new InterviewException("candidate already assigned to interview");
        });
        Interview interview = interviewSecondaryPort.save(new Interview(candidateId));
        eventHandler.publish(
            new InterviewGenerated(
                    interview.getInterviewId()
            )
        );
    }

    public void changeInterviewType(final InterviewId interviewId, final InterviewType interviewType) {
        Interview interview = find(interviewId).orElseThrow(InterviewNotFoundException::new);
        interview.changeInterviewType(interviewType);
        interviewSecondaryPort.save(interview);
        eventHandler.publish(
                interviewType == InterviewType.ONLINE
                        ? new InterviewHeldOnline(interviewId)
                        : new InterviewHeldOffline(interviewId)
        );
    }

    public void changeInterviewState(final InterviewId interviewId, final InterviewState interviewState) {
        Interview interview = find(interviewId).orElseThrow(InterviewNotFoundException::new);
        interview.changeInterviewState(interviewState);
        interviewSecondaryPort.save(interview);
        eventHandler.publish(InterviewStateEventFactory.make(interviewId, interviewState));
    }
}
