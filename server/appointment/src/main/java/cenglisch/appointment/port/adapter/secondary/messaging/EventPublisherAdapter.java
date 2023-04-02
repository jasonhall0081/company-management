package cenglisch.appointment.port.adapter.secondary.messaging;

import cenglisch.domain.model.DomainEvent;
import cenglisch.port.adapter.secondary.messaging.AbstractEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public final class EventPublisherAdapter extends AbstractEventPublisher {
    @Autowired
    private StreamBridge streamBridge;

    protected void publishExternally(final DomainEvent domainEvent) {
        Logger.getLogger(domainEvent.topic())
                .log(Level.INFO, String.valueOf(domainEvent.getClass()));

        streamBridge.send(
            domainEvent.topic(),
            domainEvent
        );
    }
}
