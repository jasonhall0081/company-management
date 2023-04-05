package cenglisch.appointment.port.adapter.secondary.persistence.appointment;

import cenglisch.appointment.domain.model.appointment.Appointment;
import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.appointment.domain.model.appointment.AppointmentRepository;
import cenglisch.appointment.domain.model.appointment.date.AppointmentDate;
import cenglisch.appointment.domain.model.appointment.date.AppointmentDateId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public final class AppointmentRepositoryAdapter implements AppointmentRepository {

    @Autowired
    private AppointmentJpaRepository appointmentJpaRepository;

    @Autowired
    private AppointmentMapper appointmentMapper;

    public List<Appointment> findAll() {
        return appointmentMapper.toAppointmentList(appointmentJpaRepository.findAll());
    }

    public Optional<Appointment> find(final AppointmentId appointmentId) {
        Optional<AppointmentEntity> optionalAppointment = appointmentJpaRepository.findById(appointmentId.id());
        return optionalAppointment.map(appointmentEntity -> appointmentMapper.toAppointment(appointmentEntity));
    }

    public Appointment save(final Appointment appointment) {
        if (appointment.getAppointmentId() == null) {
            appointment.setAppointmentId(new AppointmentId(generateId()));
        }
        AppointmentDate appointmentDate = appointment.getAppointmentDate();
        if(appointmentDate != null && appointmentDate.getAppointmentDateId() == null){
            appointmentDate.setAppointmentDateId(new AppointmentDateId(generateId()));
        }
        final AppointmentEntity appointmentEntity = appointmentMapper.toAppointmentEntity(
                appointment,
                appointment
        );
        return appointmentMapper.toAppointment(appointmentJpaRepository.save(appointmentEntity));
    }

    public void remove(final Appointment appointment) {
        throw new RuntimeException("not implemented");
    }
}
