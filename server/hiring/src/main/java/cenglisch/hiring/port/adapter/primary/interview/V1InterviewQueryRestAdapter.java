package cenglisch.hiring.port.adapter.primary.interview;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/interview")
@Tag(name = "Interview", description = "APIs für die Interviewverwaltung.")
public class V1InterviewQueryRestAdapter {
}
