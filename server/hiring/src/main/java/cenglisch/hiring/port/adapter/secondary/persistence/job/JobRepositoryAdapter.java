package cenglisch.hiring.port.adapter.secondary.persistence.job;

import cenglisch.hiring.domain.model.job.Job;
import cenglisch.hiring.domain.model.job.JobId;
import cenglisch.hiring.domain.model.job.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public final class JobRepositoryAdapter implements JobRepository {

    @Autowired
    private JobJpaRepository jobJpaRepository;

    @Autowired
    private JobMapper jobMapper;


    @Override
    public List<Job> findAll() {
        return jobMapper.mapToJobList(jobJpaRepository.findAll());
    }

    public Optional<Job> find(final JobId id) {
        Optional<JobEntity> optionalJob = jobJpaRepository.findById(id.id());
        return optionalJob.map(jobEntity -> jobMapper.mapToJob(jobEntity));
    }


    public Job save(final Job job) {
        if (job.getJobId() == null) {
            job.setJobId(new JobId(generateId()));
        }
        jobJpaRepository.saveAndFlush(
                jobMapper.mapToJobEntity(job)
        );
        return job;
    }

    @Override
    public void remove(final Job job) {
        throw new RuntimeException("not implemented");
    }
}
