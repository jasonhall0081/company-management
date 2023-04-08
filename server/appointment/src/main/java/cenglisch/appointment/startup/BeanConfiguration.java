package cenglisch.appointment.startup;

import cenglisch.appointment.application.appointment.command.AppointmentCommandApplicationPort;
import cenglisch.appointment.application.appointment.command.interview.AppointmentCommandInterviewApplicationPort;
import cenglisch.appointment.application.appointment.query.AppointmentQueryApplicationPort;
import cenglisch.appointment.application.commitment.CommitmentApplicationPort;
import cenglisch.appointment.domain.model.appointment.AppointmentService;
import cenglisch.appointment.domain.model.appointment.interview.AppointmentInterviewService;
import cenglisch.appointment.domain.model.commitment.CommitmentService;
import cenglisch.appointment.port.adapter.secondary.messaging.EventPublisherAdapter;
import cenglisch.appointment.port.adapter.secondary.persistence.appointment.AppointmentRepositoryAdapter;
import cenglisch.appointment.port.adapter.secondary.persistence.appointment.interview.AppointmentInterviewRepositoryAdapter;
import cenglisch.appointment.port.adapter.secondary.persistence.commitment.CommitmentRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SuppressWarnings("checkstyle:DesignForExtension")
public class BeanConfiguration {

    private final EventPublisherAdapter eventPublisherAdapter;

    private final AppointmentRepositoryAdapter appointmentRepositoryAdapter;

    private final AppointmentInterviewRepositoryAdapter appointmentInterviewRepositoryAdapter;

    private final CommitmentRepositoryAdapter commitmentRepositoryAdapter;

    public BeanConfiguration(
        final EventPublisherAdapter eventPublisherAdapter,
        final AppointmentRepositoryAdapter appointmentRepositoryAdapter,
        final AppointmentInterviewRepositoryAdapter appointmentInterviewRepositoryAdapter,
        final CommitmentRepositoryAdapter commitmentRepositoryAdapter
    ) {
        this.eventPublisherAdapter = eventPublisherAdapter;
        this.appointmentRepositoryAdapter = appointmentRepositoryAdapter;
        this.appointmentInterviewRepositoryAdapter = appointmentInterviewRepositoryAdapter;
        this.commitmentRepositoryAdapter = commitmentRepositoryAdapter;
    }

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
    public AppointmentQueryApplicationPort appointmentQueryApplicationPort() {
        return new AppointmentQueryApplicationPort(
                appointmentRepositoryAdapter
        );
    }

    @Bean
    public CommitmentService commitmentService() {
        return new CommitmentService(commitmentRepositoryAdapter, eventPublisherAdapter);
    }

    @Bean
    public CommitmentApplicationPort commitmentApplicationPort() {
        return new CommitmentApplicationPort(
                commitmentService(),
                appointmentService(),
                eventPublisherAdapter
        );
    }
}
