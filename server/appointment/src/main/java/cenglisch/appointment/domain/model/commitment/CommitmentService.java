package cenglisch.appointment.domain.model.commitment;

import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.appointment.domain.model.commitment.event.CommitmentConfirmed;
import cenglisch.appointment.domain.model.commitment.event.CommitmentRejected;
import cenglisch.domain.model.EventHandler;
import cenglisch.domain.model.PersonId;

import java.util.Collection;

@org.jmolecules.ddd.annotation.Service
public final class CommitmentService {

    private final CommitmentRepository commitmentRepository;
    private final EventHandler eventHandler;

    public CommitmentService(
            final CommitmentRepository commitmentRepository,
            final EventHandler eventHandler
    ) {
        this.commitmentRepository = commitmentRepository;
        this.eventHandler = eventHandler;
    }

    public boolean allParticipantAcceptedCommitment(final AppointmentId appointmentId, final int numberOfParticipants) {
        Collection<Commitment> commitments = commitmentRepository.findByAppointmentId(appointmentId);
        if (commitments.size() != numberOfParticipants) {
            return false;
        }
        for (Commitment commitment : commitments) {
            if (commitment.isCommitmentStateRejected()) {
                return false;
            }
        }
        return true;
    }

    public void giveCommitment(
            final AppointmentId appointmentId,
            final PersonId participant,
            final CommitmentState commitmentState
    ) {
        Commitment commitment = commitmentRepository.save(
                new Commitment(
                        appointmentId,
                        participant,
                        commitmentState
                )
        );
        eventHandler.publish(
                commitment.isCommitmentStateConfirmed()
                        ? new CommitmentConfirmed(commitment.getCommitmentId(), appointmentId, participant)
                        : new CommitmentRejected(commitment.getCommitmentId(), appointmentId, participant)
        );
    }
}
