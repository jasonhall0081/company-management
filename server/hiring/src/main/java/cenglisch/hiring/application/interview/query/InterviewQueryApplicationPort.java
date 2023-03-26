package cenglisch.hiring.application.interview.query;

import cenglisch.hiring.domain.model.interview.Interview;
import cenglisch.hiring.domain.model.interview.InterviewRepository;

import java.util.List;

public final class InterviewQueryApplicationPort {

    private final InterviewRepository interviewRepository;


    public InterviewQueryApplicationPort(final InterviewRepository interviewRepository) {
        this.interviewRepository = interviewRepository;
    }

    public List<Interview> showInterviews() {
        return interviewRepository.findAll();
    }
}
