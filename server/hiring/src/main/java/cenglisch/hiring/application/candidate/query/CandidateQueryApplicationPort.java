package cenglisch.hiring.application.candidate.query;

import cenglisch.hiring.domain.model.candidate.Candidate;
import cenglisch.hiring.domain.model.candidate.CandidateRepository;

import java.util.List;

public final class CandidateQueryApplicationPort {

    private final CandidateRepository candidateRepository;

    public CandidateQueryApplicationPort(final CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public List<Candidate> showApplicantCandidates(final ShowApplicantCandidates showApplicantCandidates) {
        return candidateRepository.findByJobId(showApplicantCandidates.jobId());
    }

    //filter by state
}
