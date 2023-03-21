package cenglisch.appointment.domain.model.appointment.interview;

import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.Default;

/**
 * Appointment Interview is a Proxy class for appointment.
 * It is needed to determine if a foreign system creates an appointment
*/
@org.jmolecules.ddd.annotation.Entity
public class AppointmentInterview {
    private final AppointmentInterviewId appointmentInterviewId;

    private final AppointmentId appointmentId;

    @Default
    public AppointmentInterview(AppointmentInterviewId appointmentInterviewId, AppointmentId appointmentId) {
        this.appointmentInterviewId = appointmentInterviewId;
        this.appointmentId = appointmentId;
    }

    public AppointmentInterviewId getAppointmentInterviewId() {
        return appointmentInterviewId;
    }

    public AppointmentId getAppointmentId() {
        return appointmentId;
    }
}
