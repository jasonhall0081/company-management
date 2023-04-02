package cenglisch.appointment.domain.model.appointment.event;

import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.domain.model.PersonId;

public record AppointmentCreated(AppointmentId appointmentId, PersonId initiator) implements AppointmentEvent {
    @Override
    public String getIdentifier() {
        return appointmentId.id();
    }
}
