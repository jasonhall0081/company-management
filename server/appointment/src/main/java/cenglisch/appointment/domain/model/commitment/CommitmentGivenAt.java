package cenglisch.appointment.domain.model.commitment;

import java.time.Instant;

import cenglisch.Default;
import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public class CommitmentGivenAt {

    private final Instant timestamp;

    public CommitmentGivenAt() {
        this.timestamp = Instant.now();
    }

    @Default
    public CommitmentGivenAt(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
