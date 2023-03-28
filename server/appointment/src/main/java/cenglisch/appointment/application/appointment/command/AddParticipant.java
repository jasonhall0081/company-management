package cenglisch.appointment.application.appointment.command;

import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.domain.model.PersonId;

public record AddParticipant(
        AppointmentId appointmentId,
        PersonId participantId
) {
}
