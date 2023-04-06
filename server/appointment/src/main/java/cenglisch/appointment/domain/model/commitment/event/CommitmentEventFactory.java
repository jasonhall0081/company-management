package cenglisch.appointment.domain.model.commitment.event;


import cenglisch.appointment.domain.model.commitment.Commitment;

public class CommitmentEventFactory {

    private CommitmentEventFactory(){

    }

    public static CommitmentEvent make(Commitment commitment){
        return switch (commitment.getCommitmentState()) {
            case CONFIRMED -> new CommitmentConfirmed(
                    commitment.getCommitmentId(),
                    commitment.getAppointmentId(),
                    commitment.getAppointmentDateId(),
                    commitment.getParticipant()
            );
            case REJECTED -> new CommitmentRejected(
                    commitment.getCommitmentId(),
                    commitment.getAppointmentId(),
                    commitment.getAppointmentDateId(),
                    commitment.getParticipant()
            );
            case CANCELED -> new CommitmentCanceled(
                    commitment.getCommitmentId()
            );
        };
    }
}
