package cenglisch.appointment.domain.model.commitment.event;

import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.appointment.domain.model.appointment.date.AppointmentDateId;
import cenglisch.appointment.domain.model.commitment.CommitmentId;
import cenglisch.domain.model.PersonId;

public record CommitmentRejected(
        CommitmentId commitmentId,
        AppointmentId appointmentId,
        AppointmentDateId appointmentDateId,
        PersonId personId
) implements CommitmentEvent {
    @Override
    public String getIdentifier() {
        return appointmentId.id();
    }
}
