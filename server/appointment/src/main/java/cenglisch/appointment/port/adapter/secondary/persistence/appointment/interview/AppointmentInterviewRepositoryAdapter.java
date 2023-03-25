package cenglisch.appointment.port.adapter.secondary.persistence.appointment.interview;

import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.appointment.domain.model.appointment.interview.AppointmentInterview;
import cenglisch.appointment.domain.model.appointment.interview.AppointmentInterviewId;
import cenglisch.appointment.domain.model.appointment.interview.AppointmentInterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public final class AppointmentInterviewRepositoryAdapter implements AppointmentInterviewRepository {

    @Autowired
    private AppointmentInterviewJpaRepository appointmentInterviewJpaRepository;

    @Autowired
    private AppointmentInterviewMapper appointmentInterviewMapper;

    @Override
    public Optional<AppointmentInterview> find(final AppointmentInterviewId appointmentInterviewId) {
        Optional<AppointmentInterviewEntity> optionalAppointment = appointmentInterviewJpaRepository.findById(
                appointmentInterviewId.id()
        );
        return optionalAppointment.map(
                appointmentEntity -> appointmentInterviewMapper.toAppointmentInterview(appointmentEntity)
        );
    }

    @Override
    public Optional<AppointmentInterview> findByAppointmentId(final AppointmentId appointmentId) {
        Optional<AppointmentInterviewEntity> optionalAppointment = appointmentInterviewJpaRepository
                .findByAppointmentId(appointmentId.id());
        return optionalAppointment.map(
                appointmentEntity -> appointmentInterviewMapper.toAppointmentInterview(appointmentEntity)
        );
    }

    @Override
    public AppointmentInterview save(final AppointmentInterview appointmentInterview) {
        AppointmentInterviewEntity appointmentInterviewEntity = appointmentInterviewJpaRepository.save(
                appointmentInterviewMapper.toAppointmentInterviewEntity(appointmentInterview)
        );
        return appointmentInterviewMapper.toAppointmentInterview(appointmentInterviewEntity);
    }

    @Override
    public void remove(final AppointmentInterview appointmentInterview) {
        throw new RuntimeException("not implemented");
    }
}
