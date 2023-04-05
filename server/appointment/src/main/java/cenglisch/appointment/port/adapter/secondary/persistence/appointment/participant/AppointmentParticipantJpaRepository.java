package cenglisch.appointment.port.adapter.secondary.persistence.appointment.participant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentParticipantJpaRepository extends JpaRepository<AppointmentParticipantEntity, String> {
    void deleteAllByAppointmentId(String id);
}
