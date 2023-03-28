package cenglisch.appointment.application.appointment.command;

import cenglisch.appointment.domain.model.appointment.AppointmentId;

public record CancelAppointment(AppointmentId appointmentId) {
}
