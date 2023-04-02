package cenglisch.appointment.port.adapter.secondary.persistence.commitment;

import cenglisch.appointment.domain.model.commitment.CommitmentState;
import cenglisch.appointment.port.adapter.secondary.persistence.appointment.AppointmentEntity;
import cenglisch.Default;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "commitment")
public final class CommitmentEntity {
    @Id
    @Column(name = "id", nullable = false)
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "appointment_id")
    private AppointmentEntity appointment;
    @Column(name = "participant_id")
    private String participant;
    @Enumerated(EnumType.STRING)
    @Column
    private CommitmentState commitmentState;

    public CommitmentEntity() {

    }

    @Default
    public CommitmentEntity(
            final String id,
            final AppointmentEntity appointment,
            final String participant, final CommitmentState commitmentState
    ) {
        this.id = id;
        this.appointment = appointment;
        this.participant = participant;
        this.commitmentState = commitmentState;
    }


    public String getId() {
        return id;
    }

    public AppointmentEntity getAppointment() {
        return appointment;
    }

    public String getParticipant() {
        return participant;
    }

    public CommitmentState getCommitmentState() {
        return commitmentState;
    }
}
