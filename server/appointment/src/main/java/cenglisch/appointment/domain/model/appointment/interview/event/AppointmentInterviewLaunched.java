package cenglisch.appointment.domain.model.appointment.interview.event;

import cenglisch.appointment.domain.model.appointment.interview.AppointmentInterviewId;

public record AppointmentInterviewLaunched(
        AppointmentInterviewId appointmentInterviewId
) implements AppointmentInterviewEvent {

    @Override
    public String getIdentifier() {
        return appointmentInterviewId.id();
    }
}
