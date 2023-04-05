package cenglisch.appointment.port.adapter.primary.appointment;

import cenglisch.appointment.application.appointment.command.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Appointment", description = "APIs für die Terminverwaltung.")
@RestController
@RequestMapping("v1/appointment")
public final class V1AppointmentCommandRestAdapter {

    private final AppointmentCommandApplicationPort appointmentCommandApplicationPort;

    public V1AppointmentCommandRestAdapter(final AppointmentCommandApplicationPort appointmentCommandApplicationPort) {
        this.appointmentCommandApplicationPort = appointmentCommandApplicationPort;
    }

    @PostMapping("initializeAppointment")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void initializeAppointment(@RequestBody final InitializeAppointment initializeAppointment) {
        appointmentCommandApplicationPort.initializeAppointment(initializeAppointment);
    }

    @PutMapping("appointmentRegistration")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void appointmentRegistration(@RequestBody final AppointmentRegistration appointmentRegistration) {
        appointmentCommandApplicationPort.appointmentRegistration(appointmentRegistration);
    }

    @PutMapping("rescheduleAppointment")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void rescheduleAppointment(@RequestBody final RescheduleAppointment rescheduleAppointment) {
        appointmentCommandApplicationPort.rescheduleAppointment(rescheduleAppointment);
    }

    @PutMapping("addParticipant")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addParticipant(@RequestBody final AddParticipant addParticipant) {
        appointmentCommandApplicationPort.addParticipant(addParticipant);
    }
}
