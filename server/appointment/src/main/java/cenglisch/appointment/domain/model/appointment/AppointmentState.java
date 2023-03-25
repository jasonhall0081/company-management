package cenglisch.appointment.domain.model.appointment;

@org.jmolecules.ddd.annotation.ValueObject
public enum AppointmentState {
    PENDING,
    CONFLICT,
    CANCELED,
    ACCEPTED,
    LAUNCHED,
    FINISHED
}
