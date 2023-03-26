package cenglisch.hiring.domain.model.candidate.event;

import cenglisch.hiring.domain.model.candidate.CandidateId;
import cenglisch.hiring.domain.model.job.JobId;

public record CandidateApplicationRejected(CandidateId candidateId, JobId jobId) implements CandidateEventHiring {
    @Override
    public String getIdentifier() {
        return candidateId.id();
    }
}
