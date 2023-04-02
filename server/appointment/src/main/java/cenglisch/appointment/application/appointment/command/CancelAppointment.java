package cenglisch.appointment.application.appointment.command;

import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.domain.model.DomainEvent;

public record CancelAppointment(AppointmentId appointmentId) implements DomainEvent {
    @Override
    public String getIdentifier() {
        return appointmentId.id();
    }
}
