package cenglisch.appointment.domain.model.commitment;

import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.Default;
import cenglisch.domain.model.PersonId;
import org.jmolecules.ddd.annotation.Entity;
import org.jmolecules.ddd.annotation.Identity;

@Entity
public class Commitment {
    @Identity
    private CommitmentId commitmentId;
    private AppointmentId appointmentId;
    private PersonId participant;
    private CommitmentState commitmentState;

    public Commitment(
            final AppointmentId appointmentId,
            final PersonId participant,
            final CommitmentState CommitmentState
    ) {
        setAppointmentId(appointmentId);
        setParticipant(participant);
        setCommitmentState(CommitmentState);
    }

    @Default
    public Commitment(
            final CommitmentId commitmentId,
            final AppointmentId appointmentId,
            final PersonId participant,
            final CommitmentState commitmentState
    ) {
        setCommitmentId(commitmentId);
        setAppointmentId(appointmentId);
        setParticipant(participant);
        setCommitmentState(commitmentState);
    }

    public void setCommitmentId(CommitmentId commitmentId) {
        this.commitmentId = commitmentId;
    }

    public void setParticipant(PersonId participant) {
        this.participant = participant;
    }

    public void setAppointmentId(AppointmentId appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setCommitmentState(CommitmentState commitmentState) {
        this.commitmentState = commitmentState;
    }

    public PersonId getParticipant() {
        return participant;
    }

    public boolean isCommitmentStateRejected() {
        return commitmentState == CommitmentState.REJECTED;
    }

    public boolean isCommitmentStateConfirmed() {
        return commitmentState == CommitmentState.CONFIRM;
    }

    public CommitmentState getCommitmentState() {
        return commitmentState;
    }

    public CommitmentId getCommitmentId() {
        return commitmentId;
    }

    public AppointmentId getAppointmentId() {
        return appointmentId;
    }
}