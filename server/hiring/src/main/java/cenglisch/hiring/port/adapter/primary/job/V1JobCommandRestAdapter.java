package cenglisch.hiring.port.adapter.primary.job;

import cenglisch.hiring.application.job.command.JobCommandApplicationPort;
import cenglisch.hiring.application.job.command.NewJobPosting;
import cenglisch.hiring.application.job.command.PublishJobPosting;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/job")
@Tag(name = "Job", description = "APIs für die Jobverwaltung.")
public final class V1JobCommandRestAdapter {

    private final JobCommandApplicationPort jobCommandApplicationPort;

    public V1JobCommandRestAdapter(final JobCommandApplicationPort jobCommandApplicationPort) {
        this.jobCommandApplicationPort = jobCommandApplicationPort;
    }

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
