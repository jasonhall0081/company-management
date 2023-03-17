package cenglisch.appointment.domain.model.commitment;

import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.appointment.domain.model.commitment.event.ConfirmedCommitment;
import cenglisch.appointment.domain.model.commitment.event.RejectedCommitment;
import cenglisch.domain.model.EventHandler;
import cenglisch.domain.model.PersonId;

import java.util.Collection;

public class CommitmentService {

    private final CommitmentRepository commitmentRepository;
    private final EventHandler eventHandler;

    public CommitmentService(CommitmentRepository commitmentRepository, EventHandler eventHandler) {
        this.commitmentRepository = commitmentRepository;
        this.eventHandler = eventHandler;
    }

    public boolean allParticipantAcceptedCommitment(AppointmentId appointmentId, int numberOfParticipants) {
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

    public void giveCommitment(AppointmentId appointmentId, PersonId participant, CommitmentState commitmentState) {
        Commitment commitment = commitmentRepository.save(new Commitment(appointmentId, participant, commitmentState));
        eventHandler.publish(
                commitment.isCommitmentStateConfirmed()
                        ? new ConfirmedCommitment(appointmentId, commitment.getCommitmentId())
                        : new RejectedCommitment(appointmentId, commitment.getCommitmentId()));
    }
}
