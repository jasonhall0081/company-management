package cenglisch.hiring.domain.model.interview.state;

import cenglisch.hiring.domain.model.interview.InterviewId;

public final class InterviewStateEventFactory {

    private InterviewStateEventFactory() {

    }

    public static InterviewStateEventHiring make(final InterviewId interviewId, final InterviewState interviewState) {
        return switch (interviewState) {
            case ACCEPTED -> new InterviewAccepted(interviewId);
            case LAUNCHED -> new InterviewLaunched(interviewId);
            case CARRIED_OUT -> new InterviewCarriedOut(interviewId);
            default -> throw new IllegalStateException("Unexpected value: " + interviewState);
        };
    }
}
