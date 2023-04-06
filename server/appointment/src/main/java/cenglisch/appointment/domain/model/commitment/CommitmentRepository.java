package cenglisch.appointment.domain.model.commitment;

import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.appointment.domain.model.appointment.date.AppointmentDateId;
import cenglisch.domain.model.Repository;

import java.util.Collection;

public interface CommitmentRepository extends Repository<Commitment, CommitmentId> {

    Collection<Commitment> findByAppointmentId(AppointmentId appointmentId);

    void saveAll(Collection<Commitment> commitments);

    Collection<Commitment> findByAppointmentIdAndAppointmentDateId(
            AppointmentId appointmentId,
            AppointmentDateId appointmentDateId
    );
}
