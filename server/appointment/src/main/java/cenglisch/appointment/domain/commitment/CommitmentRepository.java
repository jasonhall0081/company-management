package cenglisch.appointment.domain.commitment;

import cenglisch.appointment.domain.Repository;
import cenglisch.appointment.domain.appointment.AppointmentId;

import java.util.Collection;

public interface CommitmentRepository extends Repository<Commitment, CommitmentId> {

    Collection<Commitment> findByAppointmentId(AppointmentId appointmentId);
}