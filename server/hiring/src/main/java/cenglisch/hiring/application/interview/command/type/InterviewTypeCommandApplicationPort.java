package cenglisch.hiring.application.interview.command.type;

import cenglisch.hiring.domain.model.interview.InterviewService;
import cenglisch.hiring.domain.model.interview.type.InterviewType;

public final class InterviewTypeCommandApplicationPort {
    private final InterviewService interviewService;

    public InterviewTypeCommandApplicationPort(final InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    public void holdInterviewOnline(final HoldInterviewOnline holdInterviewOnline) {
        interviewService.changeInterviewType(holdInterviewOnline.interviewId(), InterviewType.ONLINE);
    }

    public void holdInterviewOffline(final HoldInterviewOffline holdInterviewOffline) {
        interviewService.changeInterviewType(holdInterviewOffline.interviewId(), InterviewType.OFFLINE);
    }
}
