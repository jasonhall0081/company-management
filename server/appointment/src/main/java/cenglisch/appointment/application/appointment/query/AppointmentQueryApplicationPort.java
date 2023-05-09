package cenglisch.appointment.application.appointment.query;

import cenglisch.appointment.domain.model.appointment.Appointment;
import cenglisch.appointment.domain.model.appointment.AppointmentSecondaryPort;

import java.util.List;

public final class AppointmentQueryApplicationPort {

    private final AppointmentSecondaryPort appointmentSecondaryPort;

    public AppointmentQueryApplicationPort(final AppointmentSecondaryPort appointmentSecondaryPort) {
        this.appointmentSecondaryPort = appointmentSecondaryPort;
    }

    public List<Appointment> showAppointments() {
        return appointmentSecondaryPort.findAll();
    }
}
