package cenglisch.appointment.domain.model.reminder;

import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.domain.model.PersonId;

@org.jmolecules.ddd.annotation.Entity
public class Reminder {
    private ReminderId reminderId;
    private AppointmentId appointmentId;
    private PersonId participantId;
}
