package cenglisch.hiring.port.adapter.secondary.persistence.interview;

import cenglisch.hiring.domain.model.candidate.CandidateId;
import cenglisch.hiring.domain.model.interview.Interview;
import cenglisch.hiring.domain.model.interview.InterviewId;
import cenglisch.hiring.domain.model.interview.InterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public final class InterviewRepositoryAdapter implements InterviewRepository {

    @Autowired
    private InterviewJpaRepository interviewRepository;

    @Autowired
    private InterviewMapper interviewMapper;

    public Optional<Interview> find(final InterviewId id) {
        Optional<InterviewEntity> optionalInterview = interviewRepository.findById(id.id());
        return optionalInterview.map(interviewEntity -> interviewMapper.mapToInterview(interviewEntity));
    }


    public Interview save(final Interview interview) {
        if (interview.getInterviewId() == null) {
            interview.setInterviewId(new InterviewId(generateId()));
        }
        interviewRepository.saveAndFlush(
                interviewMapper.mapToInterviewEntity(interview)
        );
        return interview;
    }

    @Override
    public void remove(final Interview interview) {
        throw new RuntimeException("not implemented");
    }

    public Optional<Interview> findByCandidateId(final CandidateId candidateId) {
        Optional<InterviewEntity> optionalInterview = interviewRepository.findByCandidateId(candidateId.id());
        return optionalInterview.map(interviewEntity -> interviewMapper.mapToInterview(interviewEntity));
    }
}
