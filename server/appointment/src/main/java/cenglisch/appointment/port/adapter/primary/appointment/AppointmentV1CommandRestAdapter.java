package cenglisch.appointment.port.adapter.primary.appointment;

import cenglisch.appointment.application.appointment.AddParticipant;
import cenglisch.appointment.application.appointment.AppointmentCommandApplicationPort;
import cenglisch.appointment.application.appointment.AppointmentRegistration;
import cenglisch.appointment.application.appointment.RescheduleAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/appointment")
public final class AppointmentV1CommandRestAdapter {
    @Autowired
    private AppointmentCommandApplicationPort appointmentCommandApplicationPort;

    @PostMapping("appointmentRegistration")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void appointmentRegistration(@RequestBody final AppointmentRegistration appointmentRegistration) {
        appointmentCommandApplicationPort.appointmentRegistration(appointmentRegistration);
    }

    @PostMapping("rescheduleAppointment")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void rescheduleAppointment(@RequestBody final RescheduleAppointment rescheduleAppointment) {
        appointmentCommandApplicationPort.rescheduleAppointment(rescheduleAppointment);
    }

    @PostMapping("addParticipant")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addParticipant(@RequestBody final AddParticipant addParticipant) {
        appointmentCommandApplicationPort.addParticipant(addParticipant);
    }
}
