package cenglisch.hiring.port.adapter.primary.interview.state;

import cenglisch.hiring.application.interview.command.state.AcceptInterview;
import cenglisch.hiring.application.interview.command.state.EndInterviewExecution;
import cenglisch.hiring.application.interview.command.state.InterviewStateCommandApplicationPort;
import cenglisch.hiring.application.interview.command.state.LaunchInterview;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/interview/state")
@Tag(name = "Interview", description = "APIs für die Interviewverwaltung.")
public final class V1InterviewStateCommandRestAdapter {

    private final InterviewStateCommandApplicationPort interviewStateCommandApplicationPort;

    public V1InterviewStateCommandRestAdapter(
            final InterviewStateCommandApplicationPort interviewStateCommandApplicationPort
    ) {
        this.interviewStateCommandApplicationPort = interviewStateCommandApplicationPort;
    }

    @PostMapping("acceptInterview")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void acceptInterview(@RequestBody final AcceptInterview acceptInterview) {
        interviewStateCommandApplicationPort.acceptInterview(acceptInterview);
    }

    @PostMapping("launchInterview")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void launchInterview(@RequestBody final LaunchInterview launchInterview) {
        interviewStateCommandApplicationPort.launchInterview(launchInterview);
    }

    @PostMapping("endInterviewExecution")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void endInterviewExecution(@RequestBody final EndInterviewExecution endInterviewExecution) {
        interviewStateCommandApplicationPort.endInterviewExecution(endInterviewExecution);
    }
}
