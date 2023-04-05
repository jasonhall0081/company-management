package cenglisch.appointment.application.appointment.command;

import cenglisch.appointment.domain.model.appointment.AppointmentId;

public record AppointmentRegistration(
        AppointmentId appointmentId,
        String date,
        String startTime,
        String endTime
) {
}
