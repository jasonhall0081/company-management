package cenglisch.hiring.port.adapter.secondary.persistence.candidate;

import cenglisch.domain.model.PersonId;
import cenglisch.hiring.domain.model.candidate.Candidate;
import cenglisch.hiring.domain.model.candidate.CandidateId;
import cenglisch.hiring.domain.model.candidate.CandidateRepository;
import cenglisch.hiring.domain.model.job.JobId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public final class CandidateRepositoryAdapter implements CandidateRepository {
    private final CandidateJpaRepository candidateRepository;
    private final CandidateMapper candidateMapper;

    public CandidateRepositoryAdapter(
        final CandidateJpaRepository candidateRepository,
        final CandidateMapper candidateMapper
    ) {
        this.candidateRepository = candidateRepository;
        this.candidateMapper = candidateMapper;
    }

    @Override
    public List<Candidate> findAll() {
        return candidateMapper.toCandidateList(candidateRepository.findAll());
    }

    public Optional<Candidate> find(final CandidateId id) {
        Optional<CandidateEntity> optionalPerson = candidateRepository.findById(id.id());
        return optionalPerson.map(candidateEntity -> candidateMapper.toCandidate(candidateEntity));
    }

    public Candidate save(final Candidate candidate) {
        if (candidate.getCandidateId() == null) {
            candidate.setCandidateId(new CandidateId(generateId()));
        }
        candidateRepository.saveAndFlush(
                candidateMapper.toCandidateEntity(candidate)
        );
        return candidate;
    }

    @Override
    public void remove(final Candidate candidate) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public boolean existsByJobId(final JobId jobId) {
        return candidateRepository.existsByJobId(jobId.id());
    }

    @Override
    public boolean existsByPersonIdAndJobId(final PersonId personId, final JobId jobId) {
        return candidateRepository.existsByPersonIdAndJobId(personId.id(), jobId.id());
    }

    @Override
    public List<Candidate> findByJobId(final JobId jobId) {
        return candidateMapper.toCandidateList(candidateRepository.findByJobId(jobId.id()));
    }
}
