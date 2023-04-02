package cenglisch.appointment.domain.model.calendar.event;

import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.appointment.domain.model.calendar.CalendarId;
import cenglisch.domain.model.DomainEvent;

public record CalendarEntryCreated(CalendarId calendarId, AppointmentId appointmentId) implements DomainEvent {
    @Override
    public String getIdentifier() {
        return null;
    }
}
