package cenglisch.appointment.domain.model.calendar;

import cenglisch.appointment.application.appointment.CancelAppointment;
import cenglisch.appointment.domain.model.commitment.CommitmentId;
import cenglisch.appointment.domain.model.commitment.event.ConfirmedCommitment;
import cenglisch.domain.model.EventHandler;
import org.jmolecules.ddd.annotation.Service;

@Service
public class CalendarService {

    private final CalendarRepository calendarRepository;
    private final EventHandler eventHandler;

    public CalendarService(CalendarRepository calendarRepository, EventHandler eventHandler) {
        this.calendarRepository = calendarRepository;
        this.eventHandler = eventHandler;

        eventHandler.subscribe(ConfirmedCommitment.class, appointment -> {
            createCalendarEntry(appointment.commitmentId());
        });

        /*eventHandler.subscribe(CancelAppointment.class, appointment -> {
            removeCalendarEntry(appointment);
        });*/
    }

    public void createCalendarEntry(CommitmentId commitmentId) {

    }

    public void removeCalendarEntry() {

    }
}
