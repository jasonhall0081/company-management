package cenglisch.appointment.application.commitment;

import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.appointment.domain.model.appointment.date.AppointmentDateId;
import cenglisch.appointment.domain.model.commitment.CommitmentState;
import cenglisch.domain.model.PersonId;

public record GiveCommitment(
        AppointmentId appointmentId,
        AppointmentDateId appointmentDateId,
        PersonId personId,
        CommitmentState commitmentState
) {
}
