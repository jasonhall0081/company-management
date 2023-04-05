package cenglisch.hiring.port.adapter.primary.interview;

import cenglisch.hiring.application.interview.query.InterviewQueryApplicationPort;
import cenglisch.hiring.domain.model.interview.Interview;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/interview")
@Tag(name = "Interview", description = "APIs für die Interviewverwaltung.")
public final class V1InterviewQueryRestAdapter {

    private final InterviewQueryApplicationPort interviewQueryApplicationPort;

    public V1InterviewQueryRestAdapter(final InterviewQueryApplicationPort interviewQueryApplicationPort) {
        this.interviewQueryApplicationPort = interviewQueryApplicationPort;
    }

    @GetMapping
    public List<Interview> getInterviews() {
        return interviewQueryApplicationPort.showInterviews();
    }
}
