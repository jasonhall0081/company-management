package com.cenglisch.appointment.domain.participant;

import com.cenglisch.appointment.domain.EventHandler;
import com.cenglisch.appointment.domain.participant.event.ParticipantCreated;
import org.jmolecules.ddd.annotation.Service;

import java.util.Optional;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;
    private final EventHandler eventHandler;

    public ParticipantService(ParticipantRepository participantRepository, EventHandler eventHandler) {
        this.participantRepository = participantRepository;
        this.eventHandler = eventHandler;
    }

    public ParticipantId newParticipant(String name, String email) {
        Participant participant = participantRepository.save(new Participant(name, email));
        eventHandler.publish(new ParticipantCreated(participant.getParticipantId()));
        return participant.getParticipantId();
    }
}