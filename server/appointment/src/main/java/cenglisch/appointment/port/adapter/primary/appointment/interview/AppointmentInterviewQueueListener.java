package cenglisch.appointment.port.adapter.primary.appointment.interview;


import cenglisch.appointment.application.appointment.command.interview.AppointmentCommandInterviewApplicationPort;
import cenglisch.appointment.application.appointment.command.interview.GenerateInterviewAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@SuppressWarnings("checkstyle:DesignForExtension")
public class AppointmentInterviewQueueListener {

    @Autowired
    private AppointmentCommandInterviewApplicationPort appointmentCommandInterviewApplicationPort;

    @Bean
    public Consumer<GenerateInterviewAppointment> generateAppointmentInterview() {
        return appointmentCommandInterviewApplicationPort::generateAppointmentInterview;
    }
}
