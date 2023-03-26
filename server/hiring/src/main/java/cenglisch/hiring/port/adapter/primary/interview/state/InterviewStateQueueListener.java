package cenglisch.hiring.port.adapter.primary.interview.state;

import cenglisch.hiring.application.interview.command.state.AcceptInterview;
import cenglisch.hiring.application.interview.command.state.EndInterviewExecution;
import cenglisch.hiring.application.interview.command.state.InterviewStateCommandApplicationPort;
import cenglisch.hiring.application.interview.command.state.LaunchInterview;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class InterviewStateQueueListener {

    @Autowired
    private InterviewStateCommandApplicationPort interviewStateCommandApplicationPort;

    @RabbitListener(queues = "appointment.interview.accepted")
    public void acceptInterview(final AcceptInterview acceptInterview) {
        interviewStateCommandApplicationPort.acceptInterview(acceptInterview);
    }

    @RabbitListener(queues = "appointment.interview.launched")
    public void launchInterview(final LaunchInterview launchInterview) {
        interviewStateCommandApplicationPort.launchInterview(launchInterview);
    }

    @RabbitListener(queues = "appointment.interview.generated")
    public void endInterviewExecution(final EndInterviewExecution endInterviewExecution) {
        interviewStateCommandApplicationPort.endInterviewExecution(endInterviewExecution);
    }
}
