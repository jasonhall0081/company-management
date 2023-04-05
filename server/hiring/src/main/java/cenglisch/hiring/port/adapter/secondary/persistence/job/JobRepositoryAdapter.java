package cenglisch.hiring.port.adapter.secondary.persistence.job;

import cenglisch.hiring.domain.model.job.Job;
import cenglisch.hiring.domain.model.job.JobId;
import cenglisch.hiring.domain.model.job.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public final class JobRepositoryAdapter implements JobRepository {

    private final JobJpaRepository jobJpaRepository;

    private final JobMapper jobMapper;

    public JobRepositoryAdapter(
        final JobJpaRepository jobJpaRepository,
        final JobMapper jobMapper
    ) {
        this.jobJpaRepository = jobJpaRepository;
        this.jobMapper = jobMapper;
    }


    @Override
    public List<Job> findAll() {
        return jobMapper.toJobList(jobJpaRepository.findAll());
    }

    public Optional<Job> find(final JobId id) {
        Optional<JobEntity> optionalJob = jobJpaRepository.findById(id.id());
        return optionalJob.map(jobEntity -> jobMapper.toJob(jobEntity));
    }


    public Job save(final Job job) {
        if (job.getJobId() == null) {
            job.setJobId(new JobId(generateId()));
        }
        jobJpaRepository.saveAndFlush(
                jobMapper.toJobEntity(job)
        );
        return job;
    }

    @Override
    public void remove(final Job job) {
        throw new RuntimeException("not implemented");
    }
}
