package cenglisch.appointment.application.calendar;

import cenglisch.appointment.application.appointment.command.CancelAppointment;
import cenglisch.appointment.domain.model.appointment.AppointmentService;
import cenglisch.appointment.domain.model.appointment.event.AppointmentCreated;
import cenglisch.appointment.domain.model.appointment.exception.AppointmentNotFoundException;
import cenglisch.appointment.domain.model.calendar.CalendarService;
import cenglisch.appointment.domain.model.commitment.event.CommitmentConfirmed;
import cenglisch.appointment.domain.model.commitment.event.CommitmentRejected;
import cenglisch.domain.model.EventHandler;

public class CalendarApplicationPort {
    private final CalendarService calendarService;
    private final AppointmentService appointmentService;
    private final EventHandler eventHandler;

    public CalendarApplicationPort(
            final CalendarService calendarService,
            final AppointmentService appointmentService,
            final EventHandler eventHandler
    ) {
        this.calendarService = calendarService;
        this.appointmentService = appointmentService;
        this.eventHandler = eventHandler;

        initializeListener();
    }

    private void initializeListener() {
        eventHandler.subscribe(CommitmentConfirmed.class, commitmentConfirmed -> {
            createCalendarEntry(
                    new CreateCalendarEntry(
                            commitmentConfirmed.appointmentId(),
                            commitmentConfirmed.personId()
                    )
            );
        });

        eventHandler.subscribe(CommitmentRejected.class, commitmentRejected -> {
            removeCalendarEntry(
                    new RemoveCalendarEntry(
                            commitmentRejected.appointmentId(),
                            commitmentRejected.personId()
                    )
            );
        });

        eventHandler.subscribe(AppointmentCreated.class, appointmentCreated -> {
            createCalendarEntry(
                    new CreateCalendarEntry(
                            appointmentCreated.appointmentId(),
                            appointmentCreated.initiator()
                    )
            );
        });

        cancelAllAppointmentDependencies();
    }

    private void cancelAllAppointmentDependencies() {
        eventHandler.subscribe(CancelAppointment.class, appointment -> {
                    appointmentService.pickUpAffectedPersons(appointment.appointmentId())
                            .forEach(personId ->
                                    removeCalendarEntry(
                                            new RemoveCalendarEntry(
                                                    appointment.appointmentId(),
                                                    personId
                                            )
                                    )
                            );
                }
        );
    }


    private void createCalendarEntry(final CreateCalendarEntry createCalendarEntry) {
        if (!appointmentService.appointmentExists(createCalendarEntry.appointmentId())) {
            throw new AppointmentNotFoundException();
        }

        calendarService.createCalendarEntry(
                createCalendarEntry.appointmentId(),
                createCalendarEntry.personId()
        );
    }

    private void removeCalendarEntry(final RemoveCalendarEntry removeCalendarEntry) {
        if (!appointmentService.appointmentExists(removeCalendarEntry.appointmentId())) {
            throw new AppointmentNotFoundException();
        }

        calendarService.removeCalendarEntry(
                removeCalendarEntry.appointmentId(),
                removeCalendarEntry.personId()
        );
    }
}
