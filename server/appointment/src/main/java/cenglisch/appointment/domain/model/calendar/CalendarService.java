package cenglisch.appointment.domain.model.calendar;

import cenglisch.appointment.domain.model.commitment.CommitmentId;
import cenglisch.appointment.domain.model.commitment.event.ConfirmedCommitment;
import cenglisch.domain.model.EventHandler;

@org.jmolecules.ddd.annotation.Service
public class CalendarService {

    private final CalendarRepository calendarRepository;
    private final EventHandler eventHandler;

    public CalendarService(final CalendarRepository calendarRepository, final EventHandler eventHandler) {
        this.calendarRepository = calendarRepository;
        this.eventHandler = eventHandler;

        eventHandler.subscribe(ConfirmedCommitment.class, appointment -> {
            createCalendarEntry(appointment.commitmentId());
        });

        /*eventHandler.subscribe(CancelAppointment.class, appointment -> {
            removeCalendarEntry(appointment);
        });*/
    }

    public void createCalendarEntry(final CommitmentId commitmentId) {

    }

    public void removeCalendarEntry() {

    }
}
