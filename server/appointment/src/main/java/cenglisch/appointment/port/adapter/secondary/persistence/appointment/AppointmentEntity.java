package cenglisch.appointment.port.adapter.secondary.persistence.appointment;

import cenglisch.Default;
import cenglisch.appointment.domain.model.appointment.AppointmentState;
import cenglisch.appointment.domain.model.appointment.AppointmentType;
import cenglisch.appointment.port.adapter.secondary.persistence.appointment.date.AppointmentDateEntity;
import cenglisch.appointment.port.adapter.secondary.persistence.appointment.participant.AppointmentParticipantEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "appointment")
public final class AppointmentEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "scheduler_id")
    private String scheduler;

    @OneToMany(
        mappedBy = "appointment",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private Collection<AppointmentParticipantEntity> participants = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "published_appointment_date_id", referencedColumnName = "id")
    private AppointmentDateEntity publishedAppointmentDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentType appointmentType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentState appointmentState;
    public AppointmentEntity() {

    }
    public AppointmentEntity(final String id) {
        this.id = id;
    }
    @Default
    public AppointmentEntity(
            final String id,
            final String scheduler,
            final Collection<AppointmentParticipantEntity> participants,
            final AppointmentDateEntity publishedAppointmentDate,
            final AppointmentType appointmentType,
            final AppointmentState appointmentState
    ) {
        this.id = id;
        this.scheduler = scheduler;
        this.participants = participants;
        this.publishedAppointmentDate = publishedAppointmentDate;
        this.appointmentType = appointmentType;
        this.appointmentState = appointmentState;
    }

    public String getId() {
        return id;
    }

    public String getscheduler() {
        return scheduler;
    }

    public Collection<AppointmentParticipantEntity> getParticipants() {
        return participants;
    }

    public AppointmentDateEntity getPublishedAppointmentDate() {
        return publishedAppointmentDate;
    }

    public AppointmentType getAppointmentType() {
        return appointmentType;
    }

    public AppointmentState getAppointmentState() {
        return appointmentState;
    }
}
