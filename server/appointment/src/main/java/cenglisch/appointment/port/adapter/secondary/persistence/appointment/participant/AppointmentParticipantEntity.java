package cenglisch.appointment.port.adapter.secondary.persistence.appointment.participant;

import cenglisch.Default;
import cenglisch.appointment.port.adapter.secondary.persistence.appointment.AppointmentEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "appointment_participant")
public final class AppointmentParticipantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id", referencedColumnName = "id")
    private AppointmentEntity appointment;

    private String participantId;
    public AppointmentParticipantEntity() {
    }

    public AppointmentParticipantEntity(final AppointmentEntity appointment, final String participantId) {
        this.appointment = appointment;
        this.participantId = participantId;
    }

    public String getId() {
        return id;
    }

    public AppointmentEntity getAppointment() {
        return appointment;
    }

    public String getParticipantId() {
        return participantId;
    }
}
