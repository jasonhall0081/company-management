package cenglisch.appointment.domain.model.commitment;

import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.Default;
import cenglisch.domain.model.PersonId;

@org.jmolecules.ddd.annotation.Entity
public final class Commitment {
    @org.jmolecules.ddd.annotation.Identity
    private CommitmentId commitmentId;
    private AppointmentId appointmentId;
    private PersonId participant;
    private CommitmentState commitmentState;

    public Commitment(
            final AppointmentId appointmentId,
            final PersonId participant,
            final CommitmentState commitmentState
    ) {
        setAppointmentId(appointmentId);
        setParticipant(participant);
        setCommitmentState(commitmentState);
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

    public void setCommitmentId(final CommitmentId commitmentId) {
        this.commitmentId = commitmentId;
    }

    public void setParticipant(final PersonId participant) {
        this.participant = participant;
    }

    public void setAppointmentId(final AppointmentId appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setCommitmentState(final CommitmentState commitmentState) {
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
