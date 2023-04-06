package cenglisch.appointment.domain.model.commitment.event;

import cenglisch.appointment.domain.model.commitment.CommitmentId;
import cenglisch.domain.model.DomainEvent;

public record CommitmentCanceled(CommitmentId commitmentId) implements CommitmentEvent {
    @Override
    public String getIdentifier() {
        return null;
    }
}
