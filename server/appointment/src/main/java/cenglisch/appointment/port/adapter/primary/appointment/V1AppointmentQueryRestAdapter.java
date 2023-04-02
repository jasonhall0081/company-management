package cenglisch.appointment.port.adapter.primary.appointment;

import cenglisch.appointment.application.appointment.query.AppointmentQueryApplicationPort;
import cenglisch.appointment.domain.model.appointment.Appointment;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/appointment")
@Tag(name = "Appointment", description = "APIs für die Terminverwaltung.")
public final class V1AppointmentQueryRestAdapter {

    @Autowired
    private AppointmentQueryApplicationPort appointmentQueryApplicationPort;

    @GetMapping
    public List<Appointment> getAppointments() {
        return appointmentQueryApplicationPort.showAppointments();
    }
}
