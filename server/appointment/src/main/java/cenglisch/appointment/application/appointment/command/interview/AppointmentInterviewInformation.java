package cenglisch.appointment.application.appointment.command.interview;

import cenglisch.appointment.domain.model.appointment.AppointmentInformation;

public class AppointmentInterviewInformation extends AppointmentInformation {

    public AppointmentInterviewInformation() {
        super(
                "Bewerbungsgespräch",
                "Einladung zum Bewerbungsgespräch",
                "Online"
        );
    }
}
