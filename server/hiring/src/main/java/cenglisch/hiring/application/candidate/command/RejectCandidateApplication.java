package cenglisch.hiring.application.candidate.command;

import cenglisch.hiring.domain.model.candidate.CandidateId;

public record RejectCandidateApplication(CandidateId candidateId) {

}
