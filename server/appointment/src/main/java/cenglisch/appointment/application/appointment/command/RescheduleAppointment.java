package cenglisch.appointment.application.appointment.command;

import cenglisch.appointment.domain.model.appointment.AppointmentId;

public record RescheduleAppointment(
        AppointmentId appointmentId,
        String date,
        String startTime,
        String endTime
) {
}
