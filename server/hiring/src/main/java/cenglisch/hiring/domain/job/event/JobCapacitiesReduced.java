package cenglisch.hiring.domain.job.event;

import cenglisch.hiring.domain.job.JobId;

public record JobCapacitiesReduced(JobId jobId) implements JobEventHiring {
}