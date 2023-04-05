package cenglisch.appointment.application.appointment.command;

import cenglisch.appointment.domain.model.appointment.AppointmentInformation;
import cenglisch.domain.model.PersonId;

public record InitializeAppointment(
        PersonId schedulingParticipant,
        AppointmentInformation appointmentInformation
) {
}
