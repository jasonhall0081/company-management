package cenglisch.appointment.domain.model.appointment;

import cenglisch.Default;
import cenglisch.appointment.domain.model.appointment.date.AppointmentDate;
import cenglisch.appointment.domain.model.appointment.date.Date;
import cenglisch.appointment.domain.model.appointment.date.Time;
import cenglisch.appointment.domain.model.appointment.exception.AppointmentException;
import cenglisch.domain.model.PersonId;

import java.util.ArrayList;
import java.util.Collection;

@org.jmolecules.ddd.annotation.AggregateRoot
public final class Appointment {

    @org.jmolecules.ddd.annotation.Identity
    private AppointmentId appointmentId;
    private PersonId scheduler;
    private final Collection<PersonId> participants;
    private AppointmentDate appointmentDate;
    private AppointmentType appointmentType;
    private AppointmentState appointmentState;
    private AppointmentInformation appointmentInformation;

    public Appointment() {
        appointmentDate = null;
        participants = new ArrayList<>();
    }

    public Appointment(
            final PersonId scheduler,
            final AppointmentInformation appointmentInformation
    ) {
        this();
        setAppointmentState(AppointmentState.INITIALIZED);
        setAppointmentType(AppointmentType.COMPULSORY_ATTENDANCE);
        setscheduler(scheduler);
        setAppointmentInformation(appointmentInformation);
    }

    @Default
    public Appointment(
            final AppointmentId appointmentId,
            final PersonId scheduler,
            final Collection<PersonId> participants,
            final AppointmentDate appointmentDate,
            final AppointmentType appointmentType,
            final AppointmentState appointmentState,
            final AppointmentInformation appointmentInformation
    ) {
        this.appointmentId = appointmentId;
        this.scheduler = scheduler;
        this.participants = participants;
        this.appointmentDate = appointmentDate;
        this.appointmentType = appointmentType;
        this.appointmentState = appointmentState;
        this.appointmentInformation = appointmentInformation;
    }

    public void registerAppointment(final String date, final String startTime, final String endTime) {
        if (!isInitalized() || appointmentDate != null) {
            throw new AppointmentException("invalid state change for appointment");
        }
        setAppointmentDate(
                new AppointmentDate(
                        appointmentId,
                        new Date(date),
                        new Time(startTime),
                        new Time(endTime)
                )
        );
        setAppointmentState(
                AppointmentState.PENDING
        );
    }

    public void rescheduleAppointment(final String date, final String startTime, final String endTime) {
        if (isLaunched() || isFinished()) {
            throw new AppointmentException("invalid state change for appointment");
        }

        var rescheduleAppointmentDate = new AppointmentDate(
                appointmentId,
                new Date(date),
                new Time(startTime),
                new Time(endTime)
        );

        if (appointmentDate.equals(rescheduleAppointmentDate)) {
            return;
        }

        setAppointmentDate(
                rescheduleAppointmentDate
        );
    }

    public void acceptAppointment() {
        if (!isPending()) {
            throw new AppointmentException("invalid state change for appointment");
        }
        setAppointmentState(AppointmentState.ACCEPTED);
    }

    public void cancelAppointment() {
        if (!isPending()) {
            throw new AppointmentException("invalid state change for appointment");
        }
        setAppointmentState(AppointmentState.CANCELED);
    }

    public void launchAppointment() {
        if (!isPending()) {
            throw new AppointmentException("invalid state change for appointment");
        }
        setAppointmentState(AppointmentState.LAUNCHED);
    }

    public void finishAppointment() {
        if (!isLaunched()) {
            throw new
                    AppointmentException("invalid state change for appointment");
        }
        setAppointmentState(AppointmentState.FINISHED);
    }

    public void addParticipant(final PersonId participant) {
        if (!isPending()) {
            throw new AppointmentException(
                    "appointment is not in a valid state for adding more participants"
            );
        }
        if (participants.contains(participant)) {
            throw new AppointmentException("participant already added to appointment");
        }
        participants.add(participant);
    }


    public boolean isCompulsoryAttendanceRequired() {
        return appointmentType == AppointmentType.COMPULSORY_ATTENDANCE;
    }

    public boolean isInitalized() {
        return appointmentState == AppointmentState.INITIALIZED;
    }

    public boolean isPending() {
        return appointmentState == AppointmentState.PENDING;
    }

    public boolean isConflict() {
        return appointmentState == AppointmentState.CONFLICT;
    }

    public boolean isCanceled() {
        return appointmentState == AppointmentState.CANCELED;
    }

    public boolean isLaunched() {
        return appointmentState == AppointmentState.LAUNCHED;
    }

    public boolean isFinished() {
        return appointmentState == AppointmentState.FINISHED;
    }

    public void setAppointmentId(final AppointmentId appointmentId) {
        this.appointmentId = appointmentId;
    }

    private void setscheduler(final PersonId scheduler) {
        this.scheduler = scheduler;
    }

    private void setAppointmentDate(final AppointmentDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    private void setAppointmentType(final AppointmentType appointmentType) {
        this.appointmentType = appointmentType;
    }

    private void setAppointmentState(final AppointmentState appointmentState) {
        this.appointmentState = appointmentState;
    }

    private void setAppointmentInformation(final AppointmentInformation appointmentInformation) {
        this.appointmentInformation = appointmentInformation;
    }

    public AppointmentId getAppointmentId() {
        return appointmentId;
    }

    public PersonId getScheduler() {
        return scheduler;
    }

    public Collection<PersonId> getParticipants() {
        return participants;
    }

    public Collection<PersonId> allParticipants() {
        Collection<PersonId> allParticipants = new ArrayList<>(participants);
        allParticipants.add(scheduler);
        return allParticipants;
    }

    public AppointmentDate getAppointmentDate() {
        return appointmentDate;
    }

    public AppointmentType getAppointmentType() {
        return appointmentType;
    }

    public AppointmentState getAppointmentState() {
        return appointmentState;
    }

    public AppointmentInformation getAppointmentInformation() {
        return appointmentInformation;
    }
}
