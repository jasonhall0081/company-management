package cenglisch.hiring.application.interview.command.state;

import cenglisch.hiring.domain.model.interview.InterviewId;

import java.io.Serializable;

public record AcceptInterview(InterviewId appointmentInterviewId) implements Serializable {
}
