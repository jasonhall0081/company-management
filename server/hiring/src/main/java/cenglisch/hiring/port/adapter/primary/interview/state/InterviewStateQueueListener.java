package cenglisch.hiring.port.adapter.primary.interview.state;

import cenglisch.hiring.application.interview.command.state.AcceptInterview;
import cenglisch.hiring.application.interview.command.state.EndInterviewExecution;
import cenglisch.hiring.application.interview.command.state.InterviewStateCommandApplicationPort;
import cenglisch.hiring.application.interview.command.state.LaunchInterview;
import cenglisch.hiring.domain.model.interview.InterviewId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@SuppressWarnings("checkstyle:DesignForExtension")
public class InterviewStateQueueListener {

    private final InterviewStateCommandApplicationPort interviewStateCommandApplicationPort;

    public InterviewStateQueueListener(
            final InterviewStateCommandApplicationPort interviewStateCommandApplicationPort
    ) {
        this.interviewStateCommandApplicationPort = interviewStateCommandApplicationPort;
    }

    @Bean
    public Consumer<AcceptInterview> acceptInterview() {
        return interviewStateCommandApplicationPort::acceptInterview;
    }

    @Bean
    public Consumer<LaunchInterview> launchInterview() {
        return interviewStateCommandApplicationPort::launchInterview;
    }

    @Bean
    public Consumer<EndInterviewExecution> endInterviewExecution() {
        return interviewStateCommandApplicationPort::endInterviewExecution;
    }
}
