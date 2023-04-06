package cenglisch.appointment.domain.model.appointment.interview;

import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.appointment.domain.model.appointment.AppointmentState;
import cenglisch.appointment.domain.model.appointment.interview.event.AppointmentInterviewEventFactory;
import cenglisch.appointment.domain.model.appointment.interview.event.AppointmentInterviewGenerated;
import cenglisch.domain.model.EventHandler;

import java.util.Optional;

@org.jmolecules.ddd.annotation.Service
public final class AppointmentInterviewService {

    private final AppointmentInterviewRepository appointmentInterviewRepository;

    private final EventHandler eventHandler;

    public AppointmentInterviewService(
            final AppointmentInterviewRepository appointmentInterviewRepository,
            final EventHandler eventHandler
    ) {
        this.appointmentInterviewRepository = appointmentInterviewRepository;
        this.eventHandler = eventHandler;
    }

    public void generateAppointmentInterview(
            final AppointmentInterviewId appointmentInterviewId,
            final AppointmentId appointmentId
    ) {
        appointmentInterviewRepository.save(new AppointmentInterview(appointmentInterviewId, appointmentId));
        eventHandler.publish(new AppointmentInterviewGenerated(appointmentInterviewId, appointmentId));
    }

    public void publishEventChange(
            final AppointmentId appointmentId,
            final AppointmentState appointmentState
    ) {
        Optional<AppointmentInterview> appointmentInterview = appointmentInterviewRepository
                .findByAppointmentId(appointmentId);

        if (appointmentInterview.isEmpty()) {
            return;
        }

        eventHandler.publish(
                AppointmentInterviewEventFactory.make(
                        appointmentInterview.get().appointmentInterviewId(),
                        appointmentState
                )
        );
    }
}
