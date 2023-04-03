package cenglisch.appointment.port.adapter.primary.commitment;

import cenglisch.appointment.application.commitment.CommitmentApplicationPort;
import cenglisch.appointment.application.commitment.GiveCommitment;
import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.appointment.domain.model.commitment.CommitmentState;
import cenglisch.domain.model.PersonId;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/commitment")
@Tag(name = "Commitment", description = "APIs für abgabe von Vereinbarungen.")
public final class V1CommitmentCommandRestAdapter {

    @Autowired
    private CommitmentApplicationPort commitmentApplicationPort;

    @PostMapping(value = "confirm", params = "appointmentId")
    public void confirmAppointment(@RequestParam final String appointmentId) {
        commitmentApplicationPort.giveCommitment(
                new GiveCommitment(
                        new AppointmentId(appointmentId),
                        new PersonId("must be implemented"),
                        CommitmentState.CONFIRM
                )
        );
    }

    @PostMapping(value = "reject", params = "appointmentId")
    public void rejectAppointment(@RequestParam final String appointmentId) {
        commitmentApplicationPort.giveCommitment(
                new GiveCommitment(
                        new AppointmentId(appointmentId),
                        new PersonId("must be implemented"),
                        CommitmentState.REJECTED
                )
        );
    }
}
