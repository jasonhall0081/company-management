package cenglisch.appointment.application.appointment.command;

import cenglisch.appointment.domain.model.appointment.Appointment;
import cenglisch.appointment.domain.model.appointment.AppointmentService;
import cenglisch.appointment.domain.model.appointment.exception.AppointmentNotFoundException;
import cenglisch.appointment.domain.model.commitment.CommitmentService;
import cenglisch.appointment.domain.model.commitment.event.ConfirmedCommitment;
import cenglisch.domain.model.EventHandler;

public final class AppointmentCommandApplicationPort {

    private final AppointmentService appointmentService;
    private final CommitmentService commitmentService;

    public AppointmentCommandApplicationPort(
            final AppointmentService appointmentService,
            final CommitmentService commitmentService,
            final EventHandler eventHandler
    ) {
        this.appointmentService = appointmentService;
        this.commitmentService = commitmentService;

        eventHandler.subscribe(ConfirmedCommitment.class, confirmedCommitment -> {
            acceptAppointment(new AcceptAppointment(confirmedCommitment.appointmentId()));
        });
    }

    public void initializeAppointment(final InitializeAppointment initializeAppointment) {
        appointmentService.initializeAppointment(
                initializeAppointment.schedulingParticipant(),
                initializeAppointment.appointmentInformation()
        );
    }

    public void appointmentRegistration(final AppointmentRegistration appointmentRegistration) {
        appointmentService.registerAppointment(
                appointmentRegistration.appointmentId(),
                appointmentRegistration.date(),
                appointmentRegistration.startTime(),
                appointmentRegistration.endTime()
        );
    }

    public void rescheduleAppointment(final RescheduleAppointment rescheduleAppointment) {
        appointmentService.rescheduleAppointment(
                rescheduleAppointment.appointmentId(),
                rescheduleAppointment.date(),
                rescheduleAppointment.startTime(),
                rescheduleAppointment.endTime()
        );
    }

    public void addParticipant(final AddParticipant addParticipant) {
        appointmentService.addParticipant(
                addParticipant.appointmentId(),
                addParticipant.participantId()
        );
    }

    private void acceptAppointment(final AcceptAppointment acceptAppointment) {
        final Appointment appointment = appointmentService.pickUpAppointment(
                acceptAppointment.appointmentId()
        ).orElseThrow(AppointmentNotFoundException::new);

        final boolean allParticipantAcceptedCommitment = commitmentService.allParticipantAcceptedCommitment(
                acceptAppointment.appointmentId(),
                appointment.getParticipants().size()
        );

        if (!allParticipantAcceptedCommitment) {
            return;
        }
        appointmentService.acceptAppointment(acceptAppointment.appointmentId());
    }

    public void launchAppointment(final LaunchAppointment launchAppointment) {
        appointmentService.launchAppointment(
                launchAppointment.appointmentId()
        );
    }

    public void finishAppointment(final FinishAppointment finishAppointment) {
        appointmentService.finishAppointment(
                finishAppointment.appointmentId()
        );
    }
}
