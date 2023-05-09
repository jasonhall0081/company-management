package cenglisch.hiring.application.job.query;

import cenglisch.hiring.domain.model.job.Job;
import cenglisch.hiring.domain.model.job.JobSecondaryPort;

import java.util.List;

public final class JobQueryApplicationPort {

    private final JobSecondaryPort jobSecondaryPort;

    public JobQueryApplicationPort(final JobSecondaryPort jobSecondaryPort) {
        this.jobSecondaryPort = jobSecondaryPort;
    }

    public List<Job> showJobs() {
        return jobSecondaryPort.findAll();
    }
}
