package cenglisch.appointment.domain.model.commitment.event;

import cenglisch.appointment.domain.model.commitment.CommitmentId;

public record CommitmentCanceled(CommitmentId commitmentId) implements CommitmentEvent {
    @Override
    public String getIdentifier() {
        return null;
    }
}
