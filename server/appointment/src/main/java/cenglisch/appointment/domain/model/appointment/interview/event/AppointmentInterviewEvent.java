package cenglisch.appointment.domain.model.appointment.interview.event;

import cenglisch.appointment.domain.model.AppointmentDomainEvent;
import cenglisch.appointment.domain.model.appointment.interview.AppointmentInterviewId;

public interface AppointmentInterviewEvent extends AppointmentDomainEvent {

    AppointmentInterviewId appointmentInterviewId();

    default String topic() {
        return AppointmentDomainEvent.super.topic() + ".appointment.interview";
    }
}
