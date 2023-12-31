package cenglisch.hiring.domain.model.interview.state;

import cenglisch.domain.model.PersonId;
import cenglisch.hiring.domain.model.interview.InterviewId;

public record InterviewGenerated(InterviewId interviewId) implements InterviewStateEventHiring {

    public String topic() {
        return InterviewStateEventHiring.super.topic() + ".generated";
    }

    @Override
    public String getIdentifier() {
        return interviewId.id();
    }
}
