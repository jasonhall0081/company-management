package cenglisch.appointment.domain.model.appointment.interview.event;

import cenglisch.appointment.domain.model.appointment.AppointmentState;
import cenglisch.appointment.domain.model.appointment.interview.AppointmentInterviewId;

public final class AppointmentInterviewEventFactory {

    private AppointmentInterviewEventFactory() {

    }

    public static AppointmentInterviewEvent make(
            final AppointmentInterviewId appointmentInterviewId,
            final AppointmentState appointmentState
    ) {
        return switch (appointmentState) {
            case ACCEPTED -> new AppointmentInterviewAccepted(appointmentInterviewId);
            case LAUNCHED -> new AppointmentInterviewLaunched(appointmentInterviewId);
            case FINISHED -> new AppointmentInterviewFinished(appointmentInterviewId);
            default -> throw new IllegalStateException("Unexpected value: " + appointmentState);
        };
    }
}
