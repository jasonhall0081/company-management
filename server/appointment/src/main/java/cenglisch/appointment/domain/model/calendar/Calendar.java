package cenglisch.appointment.domain.model.calendar;

import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.domain.model.PersonId;

import java.util.ArrayList;
import java.util.Collection;

@org.jmolecules.ddd.annotation.AggregateRoot
public final class Calendar {

    private CalendarId calendarId;

    private PersonId participant;

    private Collection<AppointmentId> appointmentIds;

    public Calendar(final PersonId participant) {
        setParticipant(participant);
        appointmentIds = new ArrayList<>();
    }

    public Calendar(final PersonId participant, final Collection<AppointmentId> appointmentIds) {
        setParticipant(participant);
        setAppointmentIds(appointmentIds);
    }

    public void createCalendarEntry(final AppointmentId appointmentId) {
        appointmentIds.add(appointmentId);
    }

    public void removeCalendarEntry(final AppointmentId appointmentId) {
        appointmentIds.remove(appointmentId);
    }

    public void setCalendarId(final CalendarId calendarId) {
        this.calendarId = calendarId;
    }

    private void setParticipant(final PersonId participant) {
        this.participant = participant;
    }

    private void setAppointmentIds(final Collection<AppointmentId> appointmentIds) {
        this.appointmentIds = appointmentIds;
    }

    public CalendarId getCalendarId() {
        return calendarId;
    }

    public PersonId getParticipant() {
        return participant;
    }

    public Collection<AppointmentId> getAppointmentIds() {
        return appointmentIds;
    }
}
