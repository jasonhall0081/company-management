package cenglisch.hiring.domain.model.candidate;


import cenglisch.domain.model.Repository;
import cenglisch.hiring.domain.model.job.JobId;
import cenglisch.domain.model.PersonId;

import java.util.List;

@org.jmolecules.ddd.annotation.Repository
public interface CandidateSecondaryPort extends Repository<Candidate, CandidateId> {
    boolean existsByJobId(JobId jobId);

    boolean existsByPersonIdAndJobId(PersonId personId, JobId jobId);

    List<Candidate> findByJobId(JobId jobId);
}
