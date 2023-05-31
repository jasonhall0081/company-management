package cenglisch.hiring.port.adapter.primary.interview;

import cenglisch.hiring.application.interview.command.state.AcceptInterview;
import cenglisch.hiring.application.interview.command.state.EndInterviewExecution;
import cenglisch.hiring.application.interview.command.state.InterviewStateCommandApplicationPort;
import cenglisch.hiring.application.interview.command.state.LaunchInterview;
import cenglisch.hiring.application.interview.command.type.HoldInterviewOffline;
import cenglisch.hiring.application.interview.command.type.HoldInterviewOnline;
import cenglisch.hiring.application.interview.command.type.InterviewTypeCommandApplicationPort;
import cenglisch.hiring.application.interview.query.InterviewQueryApplicationPort;
import cenglisch.hiring.domain.model.interview.Interview;
import cenglisch.hiring.domain.model.interview.InterviewId;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/interviews")
@Tag(name = "Interview", description = "APIs for interview management.")
public final class V1InterviewRestAdapter {

    private final InterviewStateCommandApplicationPort interviewStateCommandApplicationPort;
    private final InterviewQueryApplicationPort interviewQueryApplicationPort;
    private final InterviewTypeCommandApplicationPort interviewTypeCommandApplicationPort;

    public V1InterviewRestAdapter(
            final InterviewStateCommandApplicationPort interviewStateCommandApplicationPort,
            final InterviewQueryApplicationPort interviewQueryApplicationPort,
            final InterviewTypeCommandApplicationPort interviewTypeCommandApplicationPort
    ) {
        this.interviewStateCommandApplicationPort = interviewStateCommandApplicationPort;
        this.interviewQueryApplicationPort = interviewQueryApplicationPort;
        this.interviewTypeCommandApplicationPort = interviewTypeCommandApplicationPort;
    }

    @GetMapping
    public List<Interview> getInterviews() {
        return interviewQueryApplicationPort.showInterviews();
    }

    @PatchMapping("/{interviewId}/accept")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void acceptInterview(@PathVariable("interviewId") String interviewId) {
        interviewStateCommandApplicationPort.acceptInterview(new AcceptInterview(new InterviewId(interviewId)));
    }

    @PatchMapping("/{interviewId}/launch")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void launchInterview(@PathVariable("interviewId") String interviewId) {
        interviewStateCommandApplicationPort.launchInterview(new LaunchInterview(new InterviewId(interviewId)));
    }

    @PatchMapping("/{interviewId}/end")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void endInterviewExecution(@PathVariable("interviewId") String interviewId) {
        interviewStateCommandApplicationPort.endInterviewExecution(new EndInterviewExecution(new InterviewId(interviewId)));
    }

    @PatchMapping("/{interviewId}/holdOffline")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void holdInterviewOffline(@PathVariable("interviewId") String interviewId) {
        interviewTypeCommandApplicationPort.holdInterviewOffline(new HoldInterviewOffline(new InterviewId(interviewId)));
    }

    @PatchMapping("/{interviewId}/holdOnline")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void holdInterviewOnline(@PathVariable("interviewId") String interviewId) {
        interviewTypeCommandApplicationPort.holdInterviewOnline(new HoldInterviewOnline(new InterviewId(interviewId)));
    }
}
