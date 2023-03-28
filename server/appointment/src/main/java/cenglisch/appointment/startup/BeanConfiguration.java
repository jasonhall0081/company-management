package cenglisch.appointment.startup;

import cenglisch.appointment.application.appointment.command.AppointmentCommandApplicationPort;
import cenglisch.appointment.application.appointment.command.interview.AppointmentCommandInterviewApplicationPort;
import cenglisch.appointment.application.appointment.query.AppointmentQueryApplicationPort;
import cenglisch.appointment.domain.model.appointment.AppointmentService;
import cenglisch.appointment.domain.model.appointment.interview.AppointmentInterviewService;
import cenglisch.appointment.domain.model.commitment.CommitmentService;
import cenglisch.appointment.port.adapter.secondary.persistence.appointment.AppointmentRepositoryAdapter;
import cenglisch.appointment.port.adapter.secondary.persistence.appointment.interview.AppointmentInterviewRepositoryAdapter;
import cenglisch.appointment.port.adapter.secondary.persistence.commitment.CommitmentRepositoryAdapter;
import cenglisch.appointment.port.adapter.secondary.messaging.EventPublisherAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SuppressWarnings("checkstyle:DesignForExtension")
public class BeanConfiguration {

    @Autowired
    private EventPublisherAdapter eventPublisherAdapter;

    @Autowired
    private AppointmentRepositoryAdapter appointmentRepositoryAdapter;

    @Bean
    public AppointmentService appointmentService() {
        return new AppointmentService(appointmentRepositoryAdapter, eventPublisherAdapter);
    }

    @Bean
    public AppointmentCommandApplicationPort appointmentApplicationPort() {
        return new AppointmentCommandApplicationPort(
                appointmentService(),
                commitmentService(),
                eventPublisherAdapter
        );
    }

    @Autowired
    private CommitmentRepositoryAdapter commitmentRepositoryAdapter;

    @Bean
    public CommitmentService commitmentService() {
        return new CommitmentService(commitmentRepositoryAdapter, eventPublisherAdapter);
    }

    @Autowired
    private AppointmentInterviewRepositoryAdapter appointmentInterviewRepositoryAdapter;

    @Bean
    public AppointmentInterviewService appointmentInterviewService() {
        return new AppointmentInterviewService(appointmentInterviewRepositoryAdapter, eventPublisherAdapter);
    }

    @Bean
    public AppointmentCommandInterviewApplicationPort appointmentInterviewApplicationPort() {
        return new AppointmentCommandInterviewApplicationPort(
                appointmentInterviewService(),
                appointmentService(),
                eventPublisherAdapter
        );
    }

    @Bean
    public AppointmentQueryApplicationPort appointmentQueryApplicationPort(){
        return new AppointmentQueryApplicationPort(
                appointmentRepositoryAdapter
        );
    }
}
