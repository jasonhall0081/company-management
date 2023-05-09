package cenglisch.hiring.application.interview.query;

import cenglisch.hiring.domain.model.interview.Interview;
import cenglisch.hiring.domain.model.interview.InterviewSecondaryPort;

import java.util.List;

public final class InterviewQueryApplicationPort {

    private final InterviewSecondaryPort interviewSecondaryPort;


    public InterviewQueryApplicationPort(final InterviewSecondaryPort interviewSecondaryPort) {
        this.interviewSecondaryPort = interviewSecondaryPort;
    }

    public List<Interview> showInterviews() {
        return interviewSecondaryPort.findAll();
    }
}
