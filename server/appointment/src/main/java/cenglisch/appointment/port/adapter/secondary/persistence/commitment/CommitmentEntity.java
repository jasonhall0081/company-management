package cenglisch.appointment.port.adapter.secondary.persistence.commitment;

import cenglisch.appointment.domain.model.commitment.CommitmentState;
import cenglisch.appointment.port.adapter.secondary.persistence.appointment.AppointmentEntity;
import cenglisch.Default;

import cenglisch.appointment.port.adapter.secondary.persistence.appointment.date.AppointmentDateEntity;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "commitment")
public final class CommitmentEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id", referencedColumnName = "id")
    private AppointmentEntity appointment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_date_id", referencedColumnName = "id")
    private AppointmentDateEntity appointmentDate;

    @Column(name = "participant_id")
    private String participant;

    @Enumerated(EnumType.STRING)
    @Column
    private CommitmentState commitmentState;

    @Column()
    private Instant commitmentGivenAt;

    public CommitmentEntity() {
    }

    @Default
    public CommitmentEntity(
            final String id,
            final AppointmentEntity appointment,
            final AppointmentDateEntity appointmentDate,
            final String participant,
            final CommitmentState commitmentState,
            final Instant commitmentGivenAt
    ) {
        this.id = id;
        this.appointment = appointment;
        this.appointmentDate = appointmentDate;
        this.participant = participant;
        this.commitmentState = commitmentState;
        this.commitmentGivenAt = commitmentGivenAt;
    }

    public String getId() {
        return id;
    }

    public AppointmentEntity getAppointment() {
        return appointment;
    }

    public AppointmentDateEntity getAppointmentDate() {
        return appointmentDate;
    }

    public String getParticipant() {
        return participant;
    }

    public CommitmentState getCommitmentState() {
        return commitmentState;
    }

    public Instant getCommitmentGivenAt() {
        return commitmentGivenAt;
    }
}
