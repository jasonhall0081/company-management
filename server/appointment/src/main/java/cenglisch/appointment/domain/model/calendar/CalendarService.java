package cenglisch.appointment.domain.model.calendar;

import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.appointment.domain.model.calendar.event.CalendarEntryCreated;
import cenglisch.appointment.domain.model.calendar.event.CalendarEntryRemoved;
import cenglisch.domain.model.EventHandler;
import cenglisch.domain.model.PersonId;

import java.util.Optional;

@org.jmolecules.ddd.annotation.Service
public class CalendarService {

    private final CalendarRepository calendarRepository;
    private final EventHandler eventHandler;

    public CalendarService(final CalendarRepository calendarRepository, final EventHandler eventHandler) {
        this.calendarRepository = calendarRepository;
        this.eventHandler = eventHandler;
    }

    public Optional<Calendar> pickUpCalendar(final PersonId personId) {
        return calendarRepository.findByPerson(personId);
    }

    public void createCalendarEntry(final AppointmentId appointmentId, final PersonId personId) {
        Calendar calendar = pickUpCalendar(personId).orElse(
                calendarRepository.save(
                        new Calendar(personId)
                )
        );

        calendar.createCalendarEntry(appointmentId);
        calendarRepository.save(calendar);
        eventHandler.publish(
                new CalendarEntryCreated(
                        calendar.getCalendarId(),
                        appointmentId
                )
        );
    }

    public void removeCalendarEntry(final AppointmentId appointmentId, final PersonId personId) {
        Calendar calendar = pickUpCalendar(personId).orElseThrow(NoCalendarFoundException::new);
        calendar.removeCalendarEntry(appointmentId);

        calendarRepository.save(calendar);
        eventHandler.publish(
                new CalendarEntryRemoved(
                        calendar.getCalendarId(),
                        appointmentId
                )
        );
    }
}
