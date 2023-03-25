package cenglisch.appointment.domain.model.appointment.interview;

import cenglisch.appointment.domain.model.appointment.AppointmentId;

/**
 * Appointment Interview is a Proxy class for appointment.
 * It is needed to determine if a foreign system creates an appointment
 */
@org.jmolecules.ddd.annotation.Entity
public record AppointmentInterview(
        AppointmentInterviewId appointmentInterviewId,
        AppointmentId appointmentId
) {
}
