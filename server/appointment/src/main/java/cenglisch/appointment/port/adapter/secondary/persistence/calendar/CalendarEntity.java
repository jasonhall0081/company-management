package cenglisch.appointment.port.adapter.secondary.persistence.calendar;

import cenglisch.Default;
import cenglisch.appointment.port.adapter.secondary.persistence.appointment.AppointmentEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "calendar")
public final class CalendarEntity {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "participant", nullable = false)
    private String participant;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "calendar_appointments",
            joinColumns = @JoinColumn(name = "calendar_id"),
            inverseJoinColumns = @JoinColumn(name = "appointment_id"))
    private List<AppointmentEntity> appointments = new ArrayList<>();

    public CalendarEntity() {
    }

    @Default
    public CalendarEntity(final String id, final String participant, final List<AppointmentEntity> appointments) {
        this.id = id;
        this.participant = participant;
        this.appointments = appointments;
    }

    public String getId() {
        return id;
    }

    public String getParticipant() {
        return participant;
    }

    public List<AppointmentEntity> getAppointments() {
        return appointments;
    }
}
