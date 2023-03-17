package cenglisch.appointment.application.appointment.interview;

import cenglisch.appointment.domain.model.appointment.AppointmentService;
import cenglisch.appointment.domain.model.appointment.AppointmentState;
import cenglisch.appointment.domain.model.appointment.event.AppointmentAccepted;
import cenglisch.appointment.domain.model.appointment.event.AppointmentFinished;
import cenglisch.appointment.domain.model.appointment.event.AppointmentLaunched;
import cenglisch.appointment.domain.model.appointment.interview.AppointmentInterviewService;
import cenglisch.domain.model.EventHandler;

public class AppointmentCommandInterviewApplicationPort {

    private final AppointmentInterviewService appointmentInterviewService;

    private final AppointmentService appointmentService;

    public AppointmentCommandInterviewApplicationPort(AppointmentInterviewService appointmentInterviewService, AppointmentService appointmentService, EventHandler eventHandler) {
        this.appointmentInterviewService = appointmentInterviewService;
        this.appointmentService = appointmentService;

        eventHandler.subscribe(AppointmentAccepted.class, this::acceptAppointmentInterview);
        eventHandler.subscribe(AppointmentLaunched.class, this::launchAppointmentInterview);
        eventHandler.subscribe(AppointmentFinished.class, this::finishAppointmentInterview);
    }

    public void generateAppointmentInterview(GenerateInterviewAppointment generateInterviewAppointment) {
        appointmentInterviewService.generateAppointmentInterview(
                generateInterviewAppointment.interviewId(),
                appointmentService.initializeAppointment(
                    generateInterviewAppointment.personId()
                )
        );
    }

    private void acceptAppointmentInterview(AppointmentAccepted appointmentAccepted) {
        appointmentInterviewService.publishEventChange(
                appointmentAccepted.appointmentId(),
                AppointmentState.ACCEPTED
        );
    }

    private void launchAppointmentInterview(AppointmentLaunched appointmentLaunched) {
        appointmentInterviewService.publishEventChange(
                appointmentLaunched.appointmentId(),
                AppointmentState.LAUNCHED
        );
    }

    private void finishAppointmentInterview(AppointmentFinished appointmentFinished) {
        appointmentInterviewService.publishEventChange(
                appointmentFinished.appointmentId(),
                AppointmentState.FINISHED
        );
    }
}