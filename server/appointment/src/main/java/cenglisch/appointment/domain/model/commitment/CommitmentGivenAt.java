package cenglisch.appointment.domain.model.commitment;

import java.time.Instant;

import cenglisch.Default;

@org.jmolecules.ddd.annotation.ValueObject
public final class CommitmentGivenAt {

    private final Instant timestamp;

    public CommitmentGivenAt() {
        this.timestamp = Instant.now();
    }

    @Default
    public CommitmentGivenAt(final Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
