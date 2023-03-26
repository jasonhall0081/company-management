package cenglisch.appointment.port.adapter.primary.appointment;

import cenglisch.appointment.application.appointment.command.AddParticipant;
import cenglisch.appointment.application.appointment.command.AppointmentCommandApplicationPort;
import cenglisch.appointment.application.appointment.command.AppointmentRegistration;
import cenglisch.appointment.application.appointment.command.RescheduleAppointment;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Appointment", description = "APIs für die Terminverwaltung.")
@RestController
@RequestMapping("v1/appointment")
public final class V1AppointmentCommandRestAdapter {
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
