package cenglisch.hiring.application.job.command;

import cenglisch.hiring.domain.model.job.JobId;

public record PublishJobPosting(JobId jobId, int neededCapacities) {

}
