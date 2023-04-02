package cenglisch.appointment.application.commitment;

import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.appointment.domain.model.commitment.CommitmentState;
import cenglisch.domain.model.PersonId;

public record GiveCommitment(
        AppointmentId appointmentId,
        PersonId personId,
        CommitmentState commitmentState
) {
}
