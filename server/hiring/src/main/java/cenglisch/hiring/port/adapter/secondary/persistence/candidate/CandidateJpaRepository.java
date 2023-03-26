package cenglisch.hiring.port.adapter.secondary.persistence.candidate;

import cenglisch.hiring.domain.model.candidate.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateJpaRepository extends JpaRepository<CandidateEntity, String> {
    boolean existsByJobId(String jobId);

    boolean existsByPersonIdAndJobId(String personId, String jobId);

    List<CandidateEntity> findByJobId(String jobId);
}
