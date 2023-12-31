package cenglisch.appointment.domain.model.appointment.interview;


import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.domain.model.Repository;

import java.util.Optional;

@org.jmolecules.ddd.annotation.Repository
public interface AppointmentInterviewRepository extends Repository<AppointmentInterview, AppointmentInterviewId> {
    Optional<AppointmentInterview> findByAppointmentId(AppointmentId appointmentId);
}
