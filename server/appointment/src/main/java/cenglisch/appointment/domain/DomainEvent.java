package cenglisch.appointment.domain;

public interface DomainEvent {
    default String topic() {
        return "appointment";
    }
}