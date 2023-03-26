package cenglisch.hiring.port.adapter.primary.job;

import cenglisch.hiring.application.job.command.JobCommandApplicationPort;
import cenglisch.hiring.application.job.command.NewJobPosting;
import cenglisch.hiring.application.job.command.PublishJobPosting;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/job")
@Tag(name = "Job", description = "APIs für die Jobverwaltung.")
public final class V1JobCommandRestAdapter {

    @Autowired
    private JobCommandApplicationPort jobCommandApplicationPort;

    @PostMapping("newJobPosting")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void newJobPosting(@RequestBody final NewJobPosting newJobPosting) {
        jobCommandApplicationPort.newJobPosting(newJobPosting);
    }

    @PostMapping("publishJobPosting")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void publishJobPosting(@RequestBody final PublishJobPosting publishJobPosting) {
        jobCommandApplicationPort.publishJobPosting(publishJobPosting);
    }
}
