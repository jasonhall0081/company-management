package cenglisch.hiring.domain.job.event;

import cenglisch.hiring.domain.job.JobId;

public record JobPostingCreated(JobId jobId) implements JobEventHiring {
}
