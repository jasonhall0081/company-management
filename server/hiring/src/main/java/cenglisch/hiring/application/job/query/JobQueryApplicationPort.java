package cenglisch.hiring.application.job.query;

import cenglisch.hiring.domain.model.job.Job;
import cenglisch.hiring.domain.model.job.JobRepository;

import java.util.List;

public final class JobQueryApplicationPort {

    private final JobRepository jobRepository;

    public JobQueryApplicationPort(final JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<Job> showJobs() {
        return jobRepository.findAll();
    }
}
