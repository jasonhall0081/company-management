package cenglisch.appointment.application.appointment.command.interview;

import cenglisch.appointment.domain.model.appointment.AppointmentService;
import cenglisch.appointment.domain.model.appointment.AppointmentState;
import cenglisch.appointment.domain.model.appointment.event.AppointmentAccepted;
import cenglisch.appointment.domain.model.appointment.event.AppointmentFinished;
import cenglisch.appointment.domain.model.appointment.event.AppointmentLaunched;
import cenglisch.appointment.domain.model.appointment.interview.AppointmentInterviewService;
import cenglisch.domain.model.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class AppointmentCommandInterviewApplicationPort {

    private final static Logger LOGGER = LoggerFactory.getLogger(AppointmentCommandInterviewApplicationPort.class);

    private final AppointmentInterviewService appointmentInterviewService;

    private final AppointmentService appointmentService;

    public AppointmentCommandInterviewApplicationPort(
            final AppointmentInterviewService appointmentInterviewService,
            final AppointmentService appointmentService,
            final EventHandler eventHandler
    ) {

        this.appointmentInterviewService = appointmentInterviewService;
        this.appointmentService = appointmentService;

        eventHandler.subscribe(AppointmentAccepted.class, this::acceptAppointmentInterview);
        eventHandler.subscribe(AppointmentLaunched.class, this::launchAppointmentInterview);
        eventHandler.subscribe(AppointmentFinished.class, this::finishAppointmentInterview);
    }

    private void acceptAppointmentInterview(final AppointmentAccepted appointmentAccepted) {
        appointmentInterviewService.publishEventChange(
                appointmentAccepted.appointmentId(),
                AppointmentState.ACCEPTED
        );
    }

    private void launchAppointmentInterview(final AppointmentLaunched appointmentLaunched) {
        appointmentInterviewService.publishEventChange(
                appointmentLaunched.appointmentId(),
                AppointmentState.LAUNCHED
        );
    }

    private void finishAppointmentInterview(final AppointmentFinished appointmentFinished) {
        appointmentInterviewService.publishEventChange(
                appointmentFinished.appointmentId(),
                AppointmentState.FINISHED
        );
    }

    public void generateAppointmentInterview(final GenerateInterviewAppointment generateInterviewAppointment) {
        LOGGER.info("try to initalize appointment " + generateInterviewAppointment.interviewId());
        appointmentInterviewService.generateAppointmentInterview(
                generateInterviewAppointment.interviewId(),
                appointmentService.initializeAppointment(
                        generateInterviewAppointment.personId()
                )
        );
    }
}
