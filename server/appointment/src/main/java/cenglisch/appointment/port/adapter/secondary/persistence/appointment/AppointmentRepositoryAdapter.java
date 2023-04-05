package cenglisch.appointment.port.adapter.secondary.persistence.appointment;

import cenglisch.appointment.domain.model.appointment.Appointment;
import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.appointment.domain.model.appointment.AppointmentRepository;
import cenglisch.appointment.domain.model.appointment.date.AppointmentDate;
import cenglisch.appointment.domain.model.appointment.date.AppointmentDateId;
import cenglisch.appointment.port.adapter.secondary.persistence.appointment.participant.AppointmentParticipantJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentRepositoryAdapter implements AppointmentRepository {

    @Autowired
    private AppointmentJpaRepository appointmentJpaRepository;

    @Autowired
    private AppointmentParticipantJpaRepository appointmentParticipantJpaRepository;

    @Autowired
    private AppointmentMapper appointmentMapper;

    public final List<Appointment> findAll() {
        return appointmentMapper.toAppointmentList(appointmentJpaRepository.findAll());
    }

    public final Optional<Appointment> find(final AppointmentId appointmentId) {
        Optional<AppointmentEntity> optionalAppointment = appointmentJpaRepository.findById(appointmentId.id());
        return optionalAppointment.map(appointmentEntity -> appointmentMapper.toAppointment(appointmentEntity));
    }

    @Transactional
    @SuppressWarnings("checkstyle:DesignForExtension")
    public Appointment save(final Appointment appointment) {
        if (appointment.getAppointmentId() == null) {
            appointment.setAppointmentId(new AppointmentId(generateId()));
        }
        AppointmentDate appointmentDate = appointment.getAppointmentDate();
        if (appointmentDate != null && appointmentDate.getAppointmentDateId() == null) {
            appointmentDate.setAppointmentDateId(new AppointmentDateId(generateId()));
        }
        final AppointmentEntity appointmentEntity = appointmentMapper.toAppointmentEntity(
                appointment,
                appointment
        );

        //Aktuell gibt es keinen Appointment Participant im Domain Model
        //deswegen müssen alle Teilnehmer gelöscht und erneut eigentragen werden
        appointmentParticipantJpaRepository.deleteAllByAppointmentId(appointment.getAppointmentId().id());

        return appointmentMapper.toAppointment(appointmentJpaRepository.save(appointmentEntity));
    }

    public final void remove(final Appointment appointment) {
        throw new RuntimeException("not implemented");
    }
}
