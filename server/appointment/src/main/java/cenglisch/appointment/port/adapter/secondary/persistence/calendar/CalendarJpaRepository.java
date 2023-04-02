package cenglisch.appointment.port.adapter.secondary.persistence.calendar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CalendarJpaRepository extends JpaRepository<CalendarEntity, String> {
    Optional<CalendarEntity> findByParticipant(String id);
}
