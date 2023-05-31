package cenglisch.appointment.application.appointment.command;

public record AppointmentCommand(
        String date,
        String startTime,
        String endTime
) {
}
