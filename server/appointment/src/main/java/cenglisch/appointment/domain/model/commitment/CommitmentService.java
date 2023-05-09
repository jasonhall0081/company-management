package cenglisch.appointment.domain.model.commitment;

import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.appointment.domain.model.appointment.date.AppointmentDateId;
import cenglisch.appointment.domain.model.commitment.event.CommitmentCanceled;
import cenglisch.appointment.domain.model.commitment.event.CommitmentEventFactory;
import cenglisch.domain.model.EventHandler;
import cenglisch.domain.model.PersonId;

import java.util.Collection;

@org.jmolecules.ddd.annotation.Service
public final class CommitmentService {

    private final CommitmentSecondaryPort commitmentSecondaryPort;
    private final EventHandler eventHandler;

    public CommitmentService(
            final CommitmentSecondaryPort commitmentSecondaryPort,
            final EventHandler eventHandler
    ) {
        this.commitmentSecondaryPort = commitmentSecondaryPort;
        this.eventHandler = eventHandler;
    }

    public boolean allParticipantAcceptedCommitment(
        final AppointmentId appointmentId,
        final AppointmentDateId appointmentDateId,
        final int numberOfParticipants
    ) {
        Collection<Commitment> commitments = commitmentSecondaryPort.findByAppointmentIdAndAppointmentDateId(
                appointmentId,
                appointmentDateId
        );

        return commitments.size() == numberOfParticipants && commitments.stream().noneMatch(Commitment::isRejected);
    }

    public void cancelCommitments(final AppointmentId appointmentId) {
        Collection<Commitment> commitments = commitmentSecondaryPort.findByAppointmentId(appointmentId);
        if (commitments.isEmpty()) {
            return;
        }

        commitments.forEach(Commitment::cancel);
        commitmentSecondaryPort.saveAll(commitments);

        commitments.forEach(
            commitment -> eventHandler.publish(
                    new CommitmentCanceled(
                            commitment.getCommitmentId()
                    )
            )
        );
    }

    public void giveCommitment(
            final AppointmentId appointmentId,
            final AppointmentDateId appointmentDateId,
            final PersonId participant,
            final CommitmentState commitmentState
    ) {
        Commitment commitment = commitmentSecondaryPort.save(
                new Commitment(
                        appointmentId,
                        appointmentDateId,
                        participant,
                        commitmentState
                )
        );
        eventHandler.publish(
                CommitmentEventFactory.make(commitment)
        );
    }
}
