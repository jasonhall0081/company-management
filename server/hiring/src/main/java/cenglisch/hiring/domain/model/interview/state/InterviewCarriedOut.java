package cenglisch.hiring.domain.model.interview.state;

import cenglisch.hiring.domain.model.interview.InterviewId;

public record InterviewCarriedOut(InterviewId interviewId) implements InterviewStateEventHiring {
    @Override
    public String getIdentifier() {
        return interviewId.id();
    }
}
