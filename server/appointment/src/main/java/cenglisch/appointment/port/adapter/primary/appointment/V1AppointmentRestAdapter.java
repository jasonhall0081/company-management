package cenglisch.appointment.port.adapter.primary.appointment;

import cenglisch.appointment.application.appointment.command.*;
import cenglisch.appointment.application.appointment.query.AppointmentQueryApplicationPort;
import cenglisch.appointment.domain.model.appointment.Appointment;
import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.domain.model.PersonId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Appointment", description = "APIs für die Terminverwaltung.")
@RestController
@RequestMapping("v1/appointments")
public final class V1AppointmentRestAdapter {

    private final AppointmentCommandApplicationPort appointmentCommandApplicationPort;

    private final AppointmentQueryApplicationPort appointmentQueryApplicationPort;

    public V1AppointmentRestAdapter(final AppointmentCommandApplicationPort appointmentCommandApplicationPort, AppointmentQueryApplicationPort appointmentQueryApplicationPort) {
        this.appointmentCommandApplicationPort = appointmentCommandApplicationPort;
        this.appointmentQueryApplicationPort = appointmentQueryApplicationPort;
    }

    @GetMapping
    public List<Appointment> getAppointments() {
        return appointmentQueryApplicationPort.showAppointments();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void initializeAppointment(@RequestBody final InitializeAppointment initializeAppointment) {
        appointmentCommandApplicationPort.initializeAppointment(initializeAppointment);
    }

    @PatchMapping("{appointmentId}/registration")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void appointmentRegistration(
            @PathVariable String appointmentId,
            @RequestBody final AppointmentCommand appointmentCommand
    ) {
        appointmentCommandApplicationPort.appointmentRegistration(new AppointmentRegistration(
                new AppointmentId(appointmentId),
                appointmentCommand.date(),
                appointmentCommand.startTime(),
                appointmentCommand.endTime()
        ));
    }

    @PatchMapping("{appointmentId}/reschedule")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void rescheduleAppointment(
            @PathVariable String appointmentId,
            @RequestBody final AppointmentCommand appointmentCommand
    ) {
        appointmentCommandApplicationPort.rescheduleAppointment(new RescheduleAppointment(
                new AppointmentId(appointmentId),
                appointmentCommand.date(),
                appointmentCommand.startTime(),
                appointmentCommand.endTime()
        ));
    }


    @PatchMapping("{appointmentId}/participant/{participantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "add participant to appointment")
    public void addParticipant(
            @PathVariable String appointmentId,
            @PathVariable String participantId
    ) {
        appointmentCommandApplicationPort.addParticipant(
                new AddParticipant(
                        new AppointmentId(appointmentId), new PersonId(participantId)
                )
        );
    }
}
