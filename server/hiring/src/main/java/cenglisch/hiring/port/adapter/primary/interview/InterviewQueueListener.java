package cenglisch.hiring.port.adapter.primary.interview;

import cenglisch.hiring.application.interview.command.state.AcceptInterview;
import cenglisch.hiring.application.interview.command.state.EndInterviewExecution;
import cenglisch.hiring.application.interview.command.state.InterviewStateCommandApplicationPort;
import cenglisch.hiring.application.interview.command.state.LaunchInterview;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

public class InterviewQueueListener {

    private final InterviewStateCommandApplicationPort interviewStateCommandApplicationPort;

    public InterviewQueueListener(
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

    /*    public void interviewAppointmentGenerated(InterviewAppointment interviewAppointment){
        //TODO in query layer auslagern
        syncPersonsFromInterviewToAppointment();
    }*/
}
