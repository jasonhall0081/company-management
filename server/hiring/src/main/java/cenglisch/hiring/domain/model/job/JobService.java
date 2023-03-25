package cenglisch.hiring.domain.model.job;

import cenglisch.domain.model.EventHandler;
import cenglisch.hiring.domain.model.job.event.AddedResponsibleEmployee;
import cenglisch.hiring.domain.model.job.event.JobCapacitiesReduced;
import cenglisch.hiring.domain.model.job.event.JobEventHiring;
import cenglisch.hiring.domain.model.job.event.JobPostingCreated;
import cenglisch.hiring.domain.model.job.event.JobPostingPublished;
import cenglisch.hiring.domain.model.job.event.NoMoreCapacitiesAvailable;
import cenglisch.hiring.domain.model.job.event.RemovedResponsibleEmployee;
import cenglisch.hiring.domain.model.job.exception.JobNotFoundException;

import java.util.Optional;
import java.util.function.Consumer;

@org.jmolecules.ddd.annotation.Service
public final class JobService {

    private final JobRepository jobRepository;

    private final EventHandler eventHandler;

    public JobService(final JobRepository jobRepository, final EventHandler eventHandler) {
        this.jobRepository = jobRepository;
        this.eventHandler = eventHandler;
    }

    private Optional<Job> find(final JobId jobId) {
        return jobRepository.find(jobId);
    }

    private void manageJob(final JobId jobId, final Consumer<Job> jobConsumer, final JobEventHiring jobEvent) {
        Job job = find(jobId).orElseThrow(JobNotFoundException::new);
        jobConsumer.accept(job);
        jobRepository.save(job);
        eventHandler.publish(jobEvent);
    }

    public boolean capacitiesAvailable(final JobId jobId) {
        Job job = find(jobId).orElseThrow(JobNotFoundException::new);
        return job.isPublished();
    }

    public void newJobPosting(final String jobName, final int neededCapacities) {
        Job job = jobRepository.save(new Job(jobName, neededCapacities));
        eventHandler.publish(new JobPostingCreated(job.getJobId()));
    }

    public void publishJobPosting(final JobId jobId, final int neededCapacities) {
        manageJob(
                jobId,
                job -> job.publishJobPosting(neededCapacities),
                new JobPostingPublished(jobId)
        );
    }

    public void reduceCapacities(final JobId jobId) {
        Job job = find(jobId).orElseThrow(JobNotFoundException::new);
        job.reduceCapacities();
        jobRepository.save(job);
        if (!job.isPublished()) {
            eventHandler.publish(new NoMoreCapacitiesAvailable(job.getJobId()));
        }
        eventHandler.publish(new JobCapacitiesReduced(job.getJobId()));
    }

    public void addResponsibleEmployee(final JobId jobId, final ResponsibleEmployee responsibleEmployee) {
        manageJob(
                jobId,
                job -> job.addResponsibleEmployee(responsibleEmployee),
                new AddedResponsibleEmployee(jobId, responsibleEmployee)
        );
    }

    public void removeResponsibleEmployee(final JobId jobId, final ResponsibleEmployee responsibleEmployee) {
        manageJob(
                jobId,
                job -> job.removeResponsibleEmployee(responsibleEmployee),
                new RemovedResponsibleEmployee(jobId, responsibleEmployee)
        );
    }
}
