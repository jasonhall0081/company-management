package cenglisch.hiring.application.job.command;

import cenglisch.hiring.domain.model.job.JobId;
import cenglisch.hiring.domain.model.job.ResponsibleEmployee;

public record AddResponsibleEmployee(JobId jobId, ResponsibleEmployee responsibleEmployee) {
}
