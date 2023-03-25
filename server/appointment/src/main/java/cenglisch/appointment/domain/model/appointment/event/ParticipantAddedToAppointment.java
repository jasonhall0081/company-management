package cenglisch.appointment.domain.model.appointment.event;

import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.domain.model.PersonId;

public record ParticipantAddedToAppointment(
        AppointmentId appointmentId,
        PersonId personId
) implements AppointmentEvent {
}
