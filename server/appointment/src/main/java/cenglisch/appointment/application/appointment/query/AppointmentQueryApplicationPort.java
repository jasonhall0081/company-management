package cenglisch.appointment.application.appointment.query;

import cenglisch.appointment.domain.model.appointment.Appointment;
import cenglisch.appointment.domain.model.appointment.AppointmentRepository;

import java.util.List;

public class AppointmentQueryApplicationPort {

    private final AppointmentRepository appointmentRepository;

    public AppointmentQueryApplicationPort(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> showAppointments() {
        return appointmentRepository.findAll();
    }
}
