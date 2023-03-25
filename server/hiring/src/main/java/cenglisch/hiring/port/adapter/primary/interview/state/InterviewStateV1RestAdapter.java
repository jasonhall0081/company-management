package cenglisch.hiring.port.adapter.primary.interview.state;

import cenglisch.hiring.application.interview.state.AcceptInterview;
import cenglisch.hiring.application.interview.state.EndInterviewExecution;
import cenglisch.hiring.application.interview.state.InterviewStateCommandApplicationPort;
import cenglisch.hiring.application.interview.state.LaunchInterview;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/interview/state")
@Tag(name = "Interview", description = "APIs für die Interviewverwaltung.")
public final class InterviewStateV1RestAdapter {

    @Autowired
    private InterviewStateCommandApplicationPort interviewStateCommandApplicationPort;

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
