package cenglisch.appointment.port.adapter.secondary.persistence.calendar;

import cenglisch.appointment.domain.model.calendar.Calendar;
import cenglisch.appointment.domain.model.calendar.CalendarId;
import cenglisch.appointment.domain.model.calendar.CalendarRepository;
import cenglisch.domain.model.PersonId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public final class CalendarRepositoryAdapter implements CalendarRepository {
    @Autowired
    private CalendarJpaRepository calendarJpaRepository;

    @Autowired
    private CalendarMapper calendarMapper;

    @Override
    public List<Calendar> findAll() {
        return calendarMapper.toCalendarList(calendarJpaRepository.findAll());
    }

    @Override
    public Optional<Calendar> find(final CalendarId calendarId) {
        Optional<CalendarEntity> optionalCalendar = calendarJpaRepository.findById(calendarId.id());
        return optionalCalendar.map(calendarEntity -> calendarMapper.toCalendar(calendarEntity));
    }

    @Override
    public Calendar save(final Calendar calendar) {
        if (calendar.getCalendarId() == null) {
            calendar.setCalendarId(new CalendarId(generateId()));
        }
        final CalendarEntity calendarEntity = calendarMapper.toCalendarEntity(calendar);
        return calendarMapper.toCalendar(calendarJpaRepository.save(calendarEntity));
    }

    @Override
    public void remove(final Calendar calendar) {
        throw new RuntimeException("CalendarRepositoryAdapter.remove() not implemented");
    }

    @Override
    public Optional<Calendar> findByPerson(final PersonId personId) {
        Optional<CalendarEntity> optionalCalendar = calendarJpaRepository.findByParticipant(personId.id());
        return optionalCalendar.map(calendarEntity -> calendarMapper.toCalendar(calendarEntity));
    }

}
