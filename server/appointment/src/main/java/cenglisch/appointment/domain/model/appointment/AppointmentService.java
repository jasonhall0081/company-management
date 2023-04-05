package cenglisch.appointment.domain.model.appointment;

import cenglisch.appointment.domain.model.appointment.event.*;
import cenglisch.appointment.domain.model.appointment.exception.AppointmentNotFoundException;
import cenglisch.domain.model.EventHandler;
import cenglisch.domain.model.PersonId;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Consumer;

@org.jmolecules.ddd.annotation.Service
public final class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final EventHandler eventHandler;

    public AppointmentService(
            final AppointmentRepository appointmentRepository,
            final EventHandler eventHandler
    ) {
        this.appointmentRepository = appointmentRepository;
        this.eventHandler = eventHandler;
    }

    public Optional<Appointment> pickUpAppointment(final AppointmentId appointmentId) {
        return appointmentRepository.find(appointmentId);
    }

    public boolean appointmentExists(final AppointmentId appointmentId) {
        return pickUpAppointment(appointmentId).isPresent();
    }

    public Collection<PersonId> pickUpAffectedPersons(final AppointmentId appointmentId) {
        Appointment appointment = pickUpAppointment(appointmentId).orElseThrow(AppointmentNotFoundException::new);
        return appointment.allParticipants();
    }

    public AppointmentId initializeAppointment(
            final PersonId schedulingParticipant,
            final AppointmentInformation appointmentInformation
    ) {
        Appointment appointment = appointmentRepository.save(
                new Appointment(
                        schedulingParticipant,
                        appointmentInformation
                )
        );

        eventHandler.publish(new AppointmentCreated(
                appointment.getAppointmentId(),
                schedulingParticipant
        ));

        return appointment.getAppointmentId();
    }

    public void registerAppointment(
            final AppointmentId appointmentId,
            final String date,
            final String startTime,
            final String startDate
    ) {
        manageAppointment(
                appointmentId,
                appointment -> appointment.registerAppointment(
                        date,
                        startTime,
                        startDate
                ),
                new AppointmentCreated(
                        appointmentId,
                        pickUpAppointment(appointmentId)
                                .orElseThrow(AppointmentNotFoundException::new)
                                .getSchedulingParticipant()
                )
        );
    }

    public void rescheduleAppointment(
            final AppointmentId appointmentId,
            final String date,
            final String startTime,
            final String endTime
    ) {
        manageAppointment(
                appointmentId,
                appointment -> appointment.rescheduleAppointment(
                        date,
                        startTime,
                        endTime
                ),
                new AppointmentRescheduled(appointmentId)
        );
    }

    public void cancelAppointment(final AppointmentId appointmentId) {
        manageAppointment(
                appointmentId,
                Appointment::cancelAppointment,
                new AppointmentCanceled(appointmentId)
        );
    }

    public void acceptAppointment(final AppointmentId appointmentId) {
        manageAppointment(
                appointmentId,
                Appointment::acceptAppointment,
                new AppointmentAccepted(appointmentId)
        );
    }

    public void launchAppointment(final AppointmentId appointmentId) {
        manageAppointment(
                appointmentId,
                Appointment::launchAppointment,
                new AppointmentLaunched(appointmentId)
        );
    }

    public void finishAppointment(final AppointmentId appointmentId) {
        manageAppointment(
                appointmentId,
                Appointment::finishAppointment,
                new AppointmentFinished(appointmentId)
        );
    }

    public void addParticipant(final AppointmentId appointmentId, final PersonId participant) {
        manageAppointment(
                appointmentId,
                appointment -> appointment.addParticipant(participant),
                new ParticipantAddedToAppointment(
                        appointmentId,
                        participant
                )
        );
    }

    private void manageAppointment(
            final AppointmentId appointmentId,
            final Consumer<Appointment> appointmentConsumer,
            final AppointmentEvent appointmentEvent
    ) {
        Appointment appointment = pickUpAppointment(appointmentId)
                .orElseThrow(AppointmentNotFoundException::new);
        appointmentConsumer.accept(appointment);
        appointmentRepository.save(appointment);
        eventHandler.publish(appointmentEvent);
    }
}
