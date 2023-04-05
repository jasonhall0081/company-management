package cenglisch.appointment.port.adapter.primary.appointment.interview;


import cenglisch.appointment.application.appointment.command.interview.AppointmentCommandInterviewApplicationPort;
import cenglisch.appointment.application.appointment.command.interview.GenerateInterviewAppointment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@SuppressWarnings("checkstyle:DesignForExtension")
public class AppointmentInterviewQueueListener {

    private final AppointmentCommandInterviewApplicationPort appointmentCommandInterviewApplicationPort;

    public AppointmentInterviewQueueListener(
            final AppointmentCommandInterviewApplicationPort appointmentCommandInterviewApplicationPort
    ) {
        this.appointmentCommandInterviewApplicationPort = appointmentCommandInterviewApplicationPort;
    }

    @Bean
    public Consumer<GenerateInterviewAppointment> generateAppointmentInterview() {
        return appointmentCommandInterviewApplicationPort::generateAppointmentInterview;
    }
}
