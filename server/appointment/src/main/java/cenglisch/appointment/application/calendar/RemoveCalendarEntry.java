package cenglisch.appointment.application.calendar;

import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.domain.model.PersonId;

public record RemoveCalendarEntry(AppointmentId appointmentId, PersonId personId) {
}
