package cenglisch.appointment.domain.model.commitment;

import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.appointment.domain.model.appointment.date.AppointmentDateId;
import cenglisch.appointment.domain.model.commitment.event.CommitmentCanceled;
import cenglisch.appointment.domain.model.commitment.event.CommitmentConfirmed;
import cenglisch.appointment.domain.model.commitment.event.CommitmentEventFactory;
import cenglisch.appointment.domain.model.commitment.event.CommitmentRejected;
import cenglisch.domain.model.EventHandler;
import cenglisch.domain.model.PersonId;

import java.util.Collection;
import java.util.Optional;

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

    public boolean allParticipantAcceptedCommitment(
        final AppointmentId appointmentId,
        final AppointmentDateId appointmentDateId,
        final int numberOfParticipants
    ) {
        Collection<Commitment> commitments = commitmentRepository.findByAppointmentIdAndAppointmentDateId(
                appointmentId,
                appointmentDateId
        );

        return commitments.size() == numberOfParticipants && commitments.stream().noneMatch(Commitment::isRejected);
    }

    public void cancelCommitments(final AppointmentId appointmentId) {
        Collection<Commitment> commitments = commitmentRepository.findByAppointmentId(appointmentId);
        if (commitments.isEmpty()) {
            return;
        }

        commitments.forEach(Commitment::cancel);
        commitmentRepository.saveAll(commitments);

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
        Commitment commitment = commitmentRepository.save(
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
