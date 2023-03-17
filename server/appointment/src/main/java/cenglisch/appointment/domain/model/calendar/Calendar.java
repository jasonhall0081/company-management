package cenglisch.appointment.domain.model.calendar;

import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.domain.model.PersonId;

import java.util.ArrayList;
import java.util.Collection;

public class Calendar {

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

    public void createCalendarEntry(AppointmentId appointmentId){
        appointmentIds.add(appointmentId);
    }

    private void setCalendarId(CalendarId calendarId) {
        this.calendarId = calendarId;
    }

    private void setParticipant(PersonId participant) {
        this.participant = participant;
    }

    private void setAppointmentIds(Collection<AppointmentId> appointmentIds) {
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
