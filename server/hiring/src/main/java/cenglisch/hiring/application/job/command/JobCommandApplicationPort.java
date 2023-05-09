package cenglisch.hiring.application.job.command;

import cenglisch.domain.model.EventHandler;
import cenglisch.hiring.domain.model.candidate.CandidateSecondaryPort;
import cenglisch.hiring.domain.model.candidate.event.CandidateAdopted;
import cenglisch.hiring.domain.model.job.exception.JobException;
import cenglisch.hiring.domain.model.job.JobId;
import cenglisch.hiring.domain.model.job.JobService;

public final class JobCommandApplicationPort {
    private final JobService jobService;

    private final CandidateSecondaryPort candidateSecondaryPort;

    public JobCommandApplicationPort(
        final JobService jobService,
        final CandidateSecondaryPort candidateSecondaryPort,
        final EventHandler eventHandler
    ) {
        this.jobService = jobService;
        this.candidateSecondaryPort = candidateSecondaryPort;
        eventHandler.subscribe(
                CandidateAdopted.class, event -> {
                    reduceCapacities(new ReduceCapacities(event.jobId()));
                }
        );
    }

    private boolean jobAlreadyHasApplicants(final JobId jobId) {
        //TODO move to Query Application Port
        return candidateSecondaryPort.existsByJobId(jobId);
    }

    public void newJobPosting(final NewJobPosting newJobPosting) {
        jobService.newJobPosting(
                newJobPosting.jobName(),
                newJobPosting.neededCapacities()
        );
    }

    public void publishJobPosting(final PublishJobPosting publishJobPosting) {
        jobService.publishJobPosting(
                publishJobPosting.jobId(),
                publishJobPosting.neededCapacities()
        );
    }

    public void reduceCapacities(final ReduceCapacities reduceCapacities) {
        jobService.reduceCapacities(
                reduceCapacities.jobId()
        );
    }

    public void addResponsibleEmployee(final AddResponsibleEmployee addResponsibleEmployee) {
        if (jobAlreadyHasApplicants(addResponsibleEmployee.jobId())) {
            throw new JobException("job already has applicants");
        }
        jobService.addResponsibleEmployee(
            addResponsibleEmployee.jobId(),
            addResponsibleEmployee.responsibleEmployee()
        );
    }

    public void removeResponsibleEmployee(final RemoveResponsibleEmployee removeResponsibleEmployee) {
        if (jobAlreadyHasApplicants(removeResponsibleEmployee.jobId())) {
            throw new JobException("job already has applicants");
        }
        jobService.removeResponsibleEmployee(
                removeResponsibleEmployee.jobId(),
                removeResponsibleEmployee.responsibleEmployee()
        );
    }
}
