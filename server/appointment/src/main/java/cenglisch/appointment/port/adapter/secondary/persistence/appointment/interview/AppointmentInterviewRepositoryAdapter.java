package cenglisch.appointment.port.adapter.secondary.persistence.appointment.interview;

import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.appointment.domain.model.appointment.interview.AppointmentInterview;
import cenglisch.appointment.domain.model.appointment.interview.AppointmentInterviewId;
import cenglisch.appointment.domain.model.appointment.interview.AppointmentInterviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public final class AppointmentInterviewRepositoryAdapter implements AppointmentInterviewRepository {

    private final AppointmentInterviewJpaRepository appointmentInterviewJpaRepository;

    private final AppointmentInterviewMapper appointmentInterviewMapper;

    public AppointmentInterviewRepositoryAdapter(
        final AppointmentInterviewJpaRepository appointmentInterviewJpaRepository,
        final AppointmentInterviewMapper appointmentInterviewMapper
    ) {
        this.appointmentInterviewJpaRepository = appointmentInterviewJpaRepository;
        this.appointmentInterviewMapper = appointmentInterviewMapper;
    }

    @Override
    public List<AppointmentInterview> findAll() {
        return appointmentInterviewMapper.toAppointmentInterviewList(appointmentInterviewJpaRepository.findAll());
    }

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
