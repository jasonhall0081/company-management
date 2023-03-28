package cenglisch.appointment.application.appointment.command;

import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.appointment.domain.model.appointment.date.AppointmentDate;

public record RescheduleAppointment(AppointmentId appointmentId, AppointmentDate appointmentDate) {
}
