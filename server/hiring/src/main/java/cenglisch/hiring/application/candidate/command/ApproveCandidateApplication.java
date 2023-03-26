package cenglisch.hiring.application.candidate.command;

import cenglisch.hiring.domain.model.candidate.CandidateId;

public record ApproveCandidateApplication(CandidateId candidateId) {
}
