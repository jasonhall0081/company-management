package cenglisch.appointment.port.adapter.secondary.persistence.appointment;

import cenglisch.Default;
import cenglisch.appointment.domain.model.appointment.AppointmentInformation;
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

    @Column(name = "scheduling_participant_id")
    private String schedulingParticipant;

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

    @Embedded
    private AppointmentInformation appointmentInformation;

    public AppointmentEntity() {

    }

    public AppointmentEntity(final String id){
        this.id = id;
    }

    @Default
    public AppointmentEntity(
            final String id,
            final String schedulingParticipant,
            final Collection<AppointmentParticipantEntity> participants,
            final AppointmentDateEntity publishedAppointmentDate,
            final AppointmentType appointmentType,
            final AppointmentState appointmentState,
            final AppointmentInformation appointmentInformation
    ) {
        this.id = id;
        this.schedulingParticipant = schedulingParticipant;
        this.participants = participants;
        this.publishedAppointmentDate = publishedAppointmentDate;
        this.appointmentType = appointmentType;
        this.appointmentState = appointmentState;
        this.appointmentInformation = appointmentInformation;
    }

    public String getId() {
        return id;
    }

    public String getSchedulingParticipant() {
        return schedulingParticipant;
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

    public AppointmentInformation getAppointmentInformation() {
        return appointmentInformation;
    }
}
