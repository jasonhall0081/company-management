package cenglisch.appointment.domain.model.commitment.event;

import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.appointment.domain.model.commitment.CommitmentId;
import cenglisch.domain.model.PersonId;

public record ConfirmedCommitment(CommitmentId commitmentId, AppointmentId appointmentId,
                                  PersonId personId) implements CommitmentEvent {
    @Override
    public String getIdentifier() {
        return appointmentId.id();
    }
}
