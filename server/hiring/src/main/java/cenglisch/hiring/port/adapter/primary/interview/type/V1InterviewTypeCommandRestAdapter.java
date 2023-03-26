package cenglisch.hiring.port.adapter.primary.interview.type;

import cenglisch.hiring.application.interview.command.type.HoldInterviewOffline;
import cenglisch.hiring.application.interview.command.type.HoldInterviewOnline;
import cenglisch.hiring.application.interview.command.type.InterviewTypeCommandApplicationPort;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/interview/type")
@Tag(name = "Interview", description = "APIs für die Interviewverwaltung.")
public final class V1InterviewTypeCommandRestAdapter {

    @Autowired
    private InterviewTypeCommandApplicationPort interviewTypeCommandApplicationPort;

    @PostMapping("holdInterviewOffline")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void holdInterviewOffline(@RequestBody final HoldInterviewOffline holdInterviewOffline) {
        interviewTypeCommandApplicationPort.holdInterviewOffline(holdInterviewOffline);
    }

    @PostMapping("holdInterviewOnline")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void holdInterviewOnline(@RequestBody final HoldInterviewOnline holdInterviewOnline) {
        interviewTypeCommandApplicationPort.holdInterviewOnline(holdInterviewOnline);
    }
}
