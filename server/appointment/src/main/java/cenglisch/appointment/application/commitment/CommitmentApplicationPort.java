package cenglisch.appointment.application.commitment;

import cenglisch.appointment.domain.model.appointment.AppointmentService;
import cenglisch.appointment.domain.model.appointment.exception.AppointmentNotFoundException;
import cenglisch.appointment.domain.model.commitment.CommitmentService;

public final class CommitmentApplicationPort {

    private final CommitmentService commitmentService;

    private final AppointmentService appointmentService;

    public CommitmentApplicationPort(
            final CommitmentService commitmentService,
            final AppointmentService appointmentService
    ) {
        this.commitmentService = commitmentService;
        this.appointmentService = appointmentService;
    }

    public void giveCommitment(final GiveCommitment giveCommitment) {
        if (!appointmentService.appointmentExists(giveCommitment.appointmentId())) {
            throw new AppointmentNotFoundException();
        }

        commitmentService.giveCommitment(
                giveCommitment.appointmentId(),
                giveCommitment.personId(),
                giveCommitment.commitmentState()
        );
    }
}
