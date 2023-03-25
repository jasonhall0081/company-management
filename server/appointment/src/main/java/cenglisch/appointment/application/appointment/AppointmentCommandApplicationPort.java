package cenglisch.appointment.application.appointment;

import cenglisch.appointment.domain.model.appointment.Appointment;
import cenglisch.appointment.domain.model.appointment.exception.AppointmentNotFoundException;
import cenglisch.appointment.domain.model.appointment.AppointmentService;
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

    public void appointmentRegistration(final AppointmentRegistration appointmentRegistration) {
        appointmentService.appointmentRegistration(
                appointmentRegistration.appointmentId(),
                appointmentRegistration.schedulingParticipant(),
                appointmentRegistration.appointmentDate(),
                appointmentRegistration.appointmentType(),
                appointmentRegistration.appointmentInformation()
        );
    }

    public void rescheduleAppointment(final RescheduleAppointment rescheduleAppointment) {
        //das appointment date muss noch erstellt werden, es existsiert bis jetzt noch nicht in der db
        //entweder wird von hier aus direkt der service angestoßen
        // oder man lauscht auf das event, dass ein neues date erstellt wurde
        appointmentService.rescheduleAppointment(
                rescheduleAppointment.appointmentId(),
                rescheduleAppointment.appointmentDate()
        );
    }

    public void acceptAppointment(final AcceptAppointment acceptAppointment) {
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

    public void addParticipant(final AddParticipant addParticipant) {
        appointmentService.addParticipant(
                addParticipant.appointmentId(),
                addParticipant.participantId()
        );
    }
}
