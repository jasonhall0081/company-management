package cenglisch.hiring.port.adapter.primary.job;

import cenglisch.hiring.application.job.command.JobCommandApplicationPort;
import cenglisch.hiring.application.job.command.NewJobPosting;
import cenglisch.hiring.application.job.command.PublishJobPosting;
import cenglisch.hiring.application.job.query.JobQueryApplicationPort;
import cenglisch.hiring.domain.model.job.Job;
import cenglisch.hiring.domain.model.job.JobId;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/jobs")
@Tag(name = "Job", description = "APIs für die Jobverwaltung.")
public final class V1JobRestAdapter {

    private final JobCommandApplicationPort jobCommandApplicationPort;
    private final JobQueryApplicationPort jobQueryApplicationPort;

    public V1JobRestAdapter(final JobCommandApplicationPort jobCommandApplicationPort, final JobQueryApplicationPort jobQueryApplicationPort) {
        this.jobCommandApplicationPort = jobCommandApplicationPort;
        this.jobQueryApplicationPort = jobQueryApplicationPort;
    }

    @GetMapping
    public List<Job> getJobs() {
        return jobQueryApplicationPort.showJobs();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void newJobPosting(@RequestBody final NewJobPosting newJobPosting) {
        jobCommandApplicationPort.newJobPosting(newJobPosting);
    }

    @PatchMapping("{jobId}/publish/{neededCapacities}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void publishJobPosting(@PathVariable("jobId") String jobId,
                                  @PathVariable("neededCapacities") int neededCapacities) {
        jobCommandApplicationPort.publishJobPosting(new PublishJobPosting(new JobId(jobId), neededCapacities));
    }
}
