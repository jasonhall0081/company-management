package cenglisch.appointment.port.adapter.secondary.persistence.appointment.participant;

import cenglisch.Default;
import cenglisch.appointment.port.adapter.secondary.persistence.appointment.AppointmentEntity;
import cenglisch.domain.model.PersonId;
import jakarta.persistence.*;

@Entity
@Table(name = "appointment_participant")
public final class ParticipantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id")
    private AppointmentEntity appointment;

    @Embedded
    private PersonId personId;

    @Default
    public ParticipantEntity() {
    }

    public ParticipantEntity(final AppointmentEntity appointment, final PersonId personId) {
        this.appointment = appointment;
        this.personId = personId;
    }

    public Long getId() {
        return id;
    }

    public AppointmentEntity getAppointment() {
        return appointment;
    }

    public PersonId getPersonId() {
        return personId;
    }
}
