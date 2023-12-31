package cenglisch.hiring.port.adapter.secondary.persistence.interview;

import cenglisch.hiring.domain.model.candidate.CandidateId;
import cenglisch.hiring.domain.model.interview.Interview;
import cenglisch.hiring.domain.model.interview.InterviewId;
import cenglisch.hiring.domain.model.interview.InterviewSecondaryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public final class InterviewSecondaryPortAdapter implements InterviewSecondaryPort {

    private final InterviewJpaRepository interviewRepository;

    private final InterviewMapper interviewMapper;

    public InterviewSecondaryPortAdapter(
        final InterviewJpaRepository interviewRepository,
        final InterviewMapper interviewMapper
    ) {
        this.interviewRepository = interviewRepository;
        this.interviewMapper = interviewMapper;
    }

    @Override
    public List<Interview> findAll() {
        return interviewMapper.toInterviewList(interviewRepository.findAll());
    }

    public Optional<Interview> find(final InterviewId id) {
        Optional<InterviewEntity> optionalInterview = interviewRepository.findById(id.id());
        return optionalInterview.map(interviewEntity -> interviewMapper.toInterview(interviewEntity));
    }


    public Interview save(final Interview interview) {
        if (interview.getInterviewId() == null) {
            interview.setInterviewId(new InterviewId(generateId()));
        }
        interviewRepository.saveAndFlush(
                interviewMapper.toInterviewEntity(interview)
        );
        return interview;
    }

    @Override
    public void remove(final Interview interview) {
        throw new RuntimeException("not implemented");
    }

    public Optional<Interview> findByCandidateId(final CandidateId candidateId) {
        Optional<InterviewEntity> optionalInterview = interviewRepository.findByCandidateId(candidateId.id());
        return optionalInterview.map(interviewEntity -> interviewMapper.toInterview(interviewEntity));
    }
}
