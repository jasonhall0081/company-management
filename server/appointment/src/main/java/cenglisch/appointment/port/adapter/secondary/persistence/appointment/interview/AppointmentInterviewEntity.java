package cenglisch.appointment.port.adapter.secondary.persistence.appointment.interview;

import cenglisch.Default;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "appointment_interview")
public final class AppointmentInterviewEntity {
    @Id
    @Column
    private String id;
    @Column
    private String appointmentId;


    public AppointmentInterviewEntity() {
    }

    @Default
    public AppointmentInterviewEntity(final String id, final String appointmentId) {
        this.id = id;
        this.appointmentId = appointmentId;
    }


    public String getId() {
        return id;
    }

    public String getAppointmentId() {
        return appointmentId;
    }
}
