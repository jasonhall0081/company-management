package cenglisch.domain.model;

import java.util.function.Consumer;

public interface EventHandler {

    static EventHandler getInstance() {
        return null;
    }

    void publish(DomainEvent domainEvent);

    <T extends DomainEvent> void subscribe(Class<T> eventClass, Consumer<T> domainEventConsumer);
}
