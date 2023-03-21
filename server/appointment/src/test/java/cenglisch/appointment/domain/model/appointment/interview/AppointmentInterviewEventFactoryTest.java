package cenglisch.appointment.domain.model.appointment.interview;

import cenglisch.appointment.domain.model.appointment.AppointmentState;
import cenglisch.appointment.domain.model.appointment.interview.event.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppointmentInterviewEventFactoryTest {
    @Test
    public void testFactory() {
        var appointmentInterviewId = new AppointmentInterviewId("A-23439534");

        eventAssertion(
                new AppointmentInterviewAccepted(appointmentInterviewId),
                AppointmentInterviewEventFactory.make(
                        appointmentInterviewId,
                        AppointmentState.ACCEPTED
                )
        );

        eventAssertion(
                new AppointmentInterviewLaunched(appointmentInterviewId),
                AppointmentInterviewEventFactory.make(
                        appointmentInterviewId,
                        AppointmentState.LAUNCHED
                )
        );

        eventAssertion(
                new AppointmentInterviewFinished(appointmentInterviewId),
                AppointmentInterviewEventFactory.make(
                        appointmentInterviewId,
                        AppointmentState.FINISHED
                )
        );
    }

    public void eventAssertion(AppointmentInterviewEvent expectedEvent, AppointmentInterviewEvent buildEvent) {
        assertEquals(
                expectedEvent.getClass(),
                buildEvent.getClass()
        );
        assertEquals(
                expectedEvent.appointmentInterviewId(),
                buildEvent.appointmentInterviewId()
        );
    }

}
