package cenglisch.appointment.port.adapter.primary.commitment;

import cenglisch.appointment.application.commitment.CommitmentApplicationPort;
import cenglisch.appointment.application.commitment.GiveCommitment;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/commitment")
@Tag(name = "Commitment", description = "APIs für die Abgabe von Vereinbarungen.")
public final class V1CommitmentCommandRestAdapter {

    @Autowired
    private CommitmentApplicationPort commitmentApplicationPort;

    @PostMapping()
    public void giveCommitment(@RequestBody final GiveCommitment giveCommitment) {
        commitmentApplicationPort.giveCommitment(
                giveCommitment
        );
    }
}
