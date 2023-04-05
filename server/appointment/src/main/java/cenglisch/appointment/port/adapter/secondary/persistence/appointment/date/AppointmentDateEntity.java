package cenglisch.appointment.port.adapter.secondary.persistence.appointment.date;

import cenglisch.Default;

import cenglisch.appointment.port.adapter.secondary.persistence.appointment.AppointmentEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

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
    private String date;

    @Column
    private String startTime;

    @Column
    private String endTime;

    public AppointmentDateEntity() {

    }

    @Default
    public AppointmentDateEntity(
            final String id,
            final AppointmentEntity appointment,
            final String date,
            final String startTime,
            final String endTime
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

    public String getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
}
