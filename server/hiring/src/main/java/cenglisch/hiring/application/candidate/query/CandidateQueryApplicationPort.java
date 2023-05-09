package cenglisch.hiring.application.candidate.query;

import cenglisch.hiring.domain.model.candidate.Candidate;
import cenglisch.hiring.domain.model.candidate.CandidateSecondaryPort;

import java.util.List;

public final class CandidateQueryApplicationPort {

    private final CandidateSecondaryPort candidateSecondaryPort;

    public CandidateQueryApplicationPort(final CandidateSecondaryPort candidateSecondaryPort) {
        this.candidateSecondaryPort = candidateSecondaryPort;
    }

    public List<Candidate> showApplicantCandidates(final ShowApplicantCandidates showApplicantCandidates) {
        return candidateSecondaryPort.findByJobId(showApplicantCandidates.jobId());
    }

    //filter by state
}
