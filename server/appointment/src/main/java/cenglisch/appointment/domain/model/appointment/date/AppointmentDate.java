package cenglisch.appointment.domain.model.appointment.date;

import cenglisch.appointment.domain.model.Entity;
import cenglisch.appointment.domain.model.appointment.AppointmentId;

import java.sql.Date;
import java.sql.Time;

@org.jmolecules.ddd.annotation.Entity
public final class AppointmentDate extends Entity {

    @org.jmolecules.ddd.annotation.Identity
    private AppointmentDateId appointmentDateId;

    private AppointmentId appointmentId;

    private Date date;

    private Time startTime;

    private Time endTime;

    public AppointmentDate(
            final AppointmentId appointmentId,
            final Date date,
            final Time startTime,
            final Time endTime
    ) {
        setAppointmentId(appointmentId);
        setDate(date);
        setStartTime(startTime);
        setEndTime(endTime);
    }

    public void setAppointmentDateId(final AppointmentDateId appointmentDateId) {
        assertArgumentNotNull(appointmentDateId, "provide a value for appointment date id");
        this.appointmentDateId = appointmentDateId;
    }

    private void setAppointmentId(final AppointmentId appointmentId) {
        assertArgumentNotNull(appointmentDateId, "provide a value for appointment Ωid");
        this.appointmentId = appointmentId;
    }

    private void setDate(final Date date) {
        assertArgumentNotNull(date, "provide a value for date");
        assertArgumentFuture(date, "the date must be in future");
        this.date = date;
    }

    private void setStartTime(final Time aStartTime) {
        assertArgumentNotNull(aStartTime, "provide  a value for start time");
        assertArgumentFuture(aStartTime, "provide a start time that is in future");
        this.startTime = aStartTime;
    }

    private void setEndTime(final Time aEndTime) {
        assertArgumentNotNull(aEndTime, "provide a value for end time");
        assertArgumentFuture(aEndTime, "provide a end time that is in future");
        this.endTime = aEndTime;
    }

    public AppointmentDateId getAppointmentDateId() {
        return appointmentDateId;
    }

    public AppointmentId getAppointmentId() {
        return appointmentId;
    }

    public Date getDate() {
        return date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }
}
