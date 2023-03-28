package cenglisch.appointment.domain.model.commitment.event;

import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.appointment.domain.model.commitment.CommitmentId;

public record ConfirmedCommitment(AppointmentId appointmentId, CommitmentId commitmentId) implements CommitmentEvent {
    @Override
    public String getIdentifier() {
        return appointmentId.id();
    }
}
