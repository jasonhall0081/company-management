package cenglisch.appointment.application.appointment;

import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.appointment.domain.model.appointment.AppointmentInformation;
import cenglisch.appointment.domain.model.appointment.AppointmentType;
import cenglisch.appointment.domain.model.appointment.date.AppointmentDate;
import cenglisch.domain.model.PersonId;

public record AppointmentRegistration(
        AppointmentId appointmentId,
        PersonId schedulingParticipant,
        AppointmentDate appointmentDate,
        AppointmentType appointmentType,
        AppointmentInformation appointmentInformation
) {
}
