package cenglisch.hiring.port.adapter.primary.job;

import cenglisch.hiring.application.job.query.JobQueryApplicationPort;
import cenglisch.hiring.domain.model.job.Job;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/job")
@Tag(name = "Job", description = "APIs für die Jobverwaltung.")
public final class V1JobQueryRestAdapter {

    private final JobQueryApplicationPort jobQueryApplicationPort;

    public V1JobQueryRestAdapter(final JobQueryApplicationPort jobQueryApplicationPort) {
        this.jobQueryApplicationPort = jobQueryApplicationPort;
    }

    @GetMapping()
    public List<Job> getJobs() {
        return jobQueryApplicationPort.showJobs();
    }
}
