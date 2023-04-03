package cenglisch.appointment.port.adapter.secondary.persistence.appointment.date;

import cenglisch.appointment.port.adapter.secondary.persistence.appointment.AppointmentEntity;
import cenglisch.Default;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "appointment_date")
public final class AppointmentDateEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @OneToOne
    @JoinColumn(name = "appointment_id")
    private AppointmentEntity appointment;

    @Column
    private Date date;

    @Column
    private Time startTime;

    @Column
    private Time endTime;

    public AppointmentDateEntity() {

    }

    @Default
    public AppointmentDateEntity(
            final String id,
            final AppointmentEntity appointment,
            final Date date,
            final Time startTime,
            final Time endTime
    ) {
        this.id = id;
        this.appointment = appointment;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getId() {
        return id;
    }

    public AppointmentEntity getAppointment() {
        return appointment;
    }

    public Date getDate() {
        return date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }
}
