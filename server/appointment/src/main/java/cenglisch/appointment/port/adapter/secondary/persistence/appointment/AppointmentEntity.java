package cenglisch.appointment.port.adapter.secondary.persistence.appointment;

import cenglisch.appointment.domain.model.appointment.AppointmentInformation;
import cenglisch.appointment.domain.model.appointment.AppointmentState;
import cenglisch.appointment.domain.model.appointment.AppointmentType;
import cenglisch.appointment.port.adapter.secondary.persistence.appointment.date.AppointmentDateEntity;
import cenglisch.Default;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.Collection;

@Entity
@Table(name = "appointment")
public final class AppointmentEntity {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "scheduling_participant_id")
    private String schedulingParticipant;

    //private Collection<String> participants;

    @OneToOne(fetch = FetchType.LAZY)
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

    @Default
    public AppointmentEntity(
        final String id,
        final String schedulingParticipant,
        //final Collection<String> participants,
        final AppointmentDateEntity publishedAppointmentDate,
        final AppointmentType appointmentType,
        final AppointmentState appointmentState,
        final AppointmentInformation appointmentInformation
    ) {
        this.id = id;
        this.schedulingParticipant = schedulingParticipant;
        //this.participants = participants;
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

    /*public Collection<String> getParticipants() {
        return participants;
    }*/

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
