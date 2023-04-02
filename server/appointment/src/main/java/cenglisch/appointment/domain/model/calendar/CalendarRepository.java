package cenglisch.appointment.domain.model.calendar;

import cenglisch.domain.model.PersonId;
import cenglisch.domain.model.Repository;

import java.util.Optional;

@org.jmolecules.ddd.annotation.Repository
public interface CalendarRepository extends Repository<Calendar, CalendarId> {
    Optional<Calendar> findByPerson(PersonId personId);
}
