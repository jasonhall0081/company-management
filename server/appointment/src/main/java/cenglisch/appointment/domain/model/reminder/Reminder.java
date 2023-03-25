package cenglisch.appointment.domain.model.reminder;

import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.domain.model.PersonId;

public class Reminder {
    private ReminderId reminderId;
    private AppointmentId appointmentId;
    private PersonId participantId;
}
