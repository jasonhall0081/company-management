package cenglisch.appointment.application.commitment;

import cenglisch.appointment.domain.model.appointment.AppointmentService;
import cenglisch.appointment.domain.model.appointment.event.AppointmentRescheduled;
import cenglisch.appointment.domain.model.appointment.exception.AppointmentNotFoundException;
import cenglisch.appointment.domain.model.commitment.CommitmentService;
import cenglisch.domain.model.EventHandler;

public final class CommitmentApplicationPort {

    private final CommitmentService commitmentService;

    private final AppointmentService appointmentService;

    public CommitmentApplicationPort(
            final CommitmentService commitmentService,
            final AppointmentService appointmentService,
            final EventHandler eventHandler
    ) {
        this.commitmentService = commitmentService;
        this.appointmentService = appointmentService;

        eventHandler.subscribe(AppointmentRescheduled.class, appointmentRescheduled ->
            cancelCommitments(
                new CancelCommitments(
                    appointmentRescheduled.appointmentId()
                )
            )
        );
    }

    public void cancelCommitments(final CancelCommitments cancelCommitments) {
        commitmentService.cancelCommitments(
                cancelCommitments.appointmentId()
        );
    }

    public void giveCommitment(final GiveCommitment giveCommitment) {
        if (!appointmentService.appointmentExists(giveCommitment.appointmentId())) {
            throw new AppointmentNotFoundException();
        }

        commitmentService.giveCommitment(
                giveCommitment.appointmentId(),
                giveCommitment.appointmentDateId(),
                giveCommitment.personId(),
                giveCommitment.commitmentState()
        );
    }
}
