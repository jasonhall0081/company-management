package cenglisch.appointment.domain.model.commitment;

import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.Default;
import cenglisch.appointment.domain.model.appointment.date.AppointmentDateId;
import cenglisch.domain.model.PersonId;

@org.jmolecules.ddd.annotation.Entity
public final class Commitment {
    @org.jmolecules.ddd.annotation.Identity
    private CommitmentId commitmentId;
    private AppointmentId appointmentId;
    private AppointmentDateId appointmentDateId;
    private PersonId participant;
    private CommitmentState commitmentState;
    private CommitmentGivenAt commitmentGivenAt;

    public Commitment(
            final AppointmentId appointmentId,
            final AppointmentDateId appointmentDateId,
            final PersonId participant,
            final CommitmentState commitmentState
    ) {
        setAppointmentId(appointmentId);
        setAppointmentDateId(appointmentDateId);
        setParticipant(participant);
        setCommitmentState(commitmentState);
        setCommitmentGivenAt(new CommitmentGivenAt());
    }

    @Default
    public Commitment(
            final CommitmentId commitmentId,
            final AppointmentId appointmentId,
            final AppointmentDateId appointmentDateId,
            final PersonId participant,
            final CommitmentState commitmentState,
            final CommitmentGivenAt commitmentGivenAt
    ) {
        setCommitmentId(commitmentId);
        setAppointmentDateId(appointmentDateId);
        setAppointmentId(appointmentId);
        setParticipant(participant);
        setCommitmentState(commitmentState);
        setCommitmentGivenAt(commitmentGivenAt);
    }

    public void confirm() {
        setCommitmentState(CommitmentState.CONFIRMED);
    }

    public void reject() {
        setCommitmentState(CommitmentState.REJECTED);
    }

    public void cancel() {
        setCommitmentState(CommitmentState.CANCELED);
    }

    public void setCommitmentId(final CommitmentId commitmentId) {
        this.commitmentId = commitmentId;
    }

    private void setParticipant(final PersonId participant) {
        this.participant = participant;
    }

    private void setAppointmentId(final AppointmentId appointmentId) {
        this.appointmentId = appointmentId;
    }
    private void setAppointmentDateId(AppointmentDateId appointmentDateId) {
        this.appointmentDateId = appointmentDateId;
    }

    private void setCommitmentState(final CommitmentState commitmentState) {
        this.commitmentState = commitmentState;
    }

    private void setCommitmentGivenAt(final CommitmentGivenAt commitmentGivenAt) {
        this.commitmentGivenAt = commitmentGivenAt;
    }

    public PersonId getParticipant() {
        return participant;
    }

    public boolean isRejected() {
        return commitmentState == CommitmentState.REJECTED;
    }

    public boolean isConfirmed() {
        return commitmentState == CommitmentState.CONFIRMED;
    }

    public boolean isCanceled() {
        return commitmentState == CommitmentState.CANCELED;
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

    public AppointmentDateId getAppointmentDateId() {
        return appointmentDateId;
    }
    public CommitmentGivenAt getCommitmentGivenAt() {
        return commitmentGivenAt;
    }
}
