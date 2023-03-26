package cenglisch.hiring.application.candidate.command;

import cenglisch.hiring.domain.model.job.JobId;
import cenglisch.domain.model.PersonId;

public record CandidateApplies(PersonId personId, JobId jobId) {
}
