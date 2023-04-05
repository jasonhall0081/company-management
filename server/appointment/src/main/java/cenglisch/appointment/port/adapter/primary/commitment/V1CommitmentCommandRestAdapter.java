package cenglisch.appointment.port.adapter.primary.commitment;

import cenglisch.appointment.application.commitment.CommitmentApplicationPort;
import cenglisch.appointment.application.commitment.GiveCommitment;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/commitment")
@Tag(name = "Commitment", description = "APIs für die Abgabe von Vereinbarungen.")
public final class V1CommitmentCommandRestAdapter {

    private final CommitmentApplicationPort commitmentApplicationPort;

    public V1CommitmentCommandRestAdapter(final CommitmentApplicationPort commitmentApplicationPort) {
        this.commitmentApplicationPort = commitmentApplicationPort;
    }

    @PostMapping()
    public void giveCommitment(@RequestBody final GiveCommitment giveCommitment) {
        commitmentApplicationPort.giveCommitment(
                giveCommitment
        );
    }
}
