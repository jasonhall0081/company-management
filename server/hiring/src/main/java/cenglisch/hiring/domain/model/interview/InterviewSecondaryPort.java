package cenglisch.hiring.domain.model.interview;

import cenglisch.domain.model.Repository;
import cenglisch.hiring.domain.model.candidate.CandidateId;

import java.util.Optional;

@org.jmolecules.ddd.annotation.Repository
public interface InterviewSecondaryPort extends Repository<Interview, InterviewId> {
    Optional<Interview> findByCandidateId(CandidateId candidateId);
}
