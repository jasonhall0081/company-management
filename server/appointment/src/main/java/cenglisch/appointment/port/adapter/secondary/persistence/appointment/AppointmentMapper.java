package cenglisch.appointment.port.adapter.secondary.persistence.appointment;

import cenglisch.appointment.domain.model.appointment.Appointment;
import cenglisch.appointment.port.adapter.secondary.persistence.appointment.date.AppointmentDateMapper;
import cenglisch.appointment.port.adapter.secondary.persistence.appointment.participant.AppointmentParticipantEntity;
import cenglisch.domain.model.PersonId;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {
                AppointmentDateMapper.class
        }
)
public interface AppointmentMapper {

    @Mapping(target = "appointmentId.id", source = "id")
    @Mapping(target = "scheduler.id", source = "scheduler")
    @Mapping(
        target = "participants",
        source = "participants",
        qualifiedByName = "toPersonIds"
    )
    @Mapping(
        target = "appointmentDate",
        source = "publishedAppointmentDate",
        qualifiedByName = "toAppointmentDate"
    )
    @Named("toAppointment")
    Appointment toAppointment(AppointmentEntity appointmentEntity);

    @Named("toPersonIds")
    default Collection<PersonId> toPersonIds(Collection<AppointmentParticipantEntity> participants) {
        return participants.stream()
                .map(AppointmentParticipantEntity::getParticipantId)
                .map(PersonId::new)
                .collect(Collectors.toList());
    }

    @IterableMapping(qualifiedByName = "toAppointment")
    List<Appointment> toAppointmentList(List<AppointmentEntity> appointmentEntityList);

    @Mapping(target = "id", source = "appointmentId.id")
    @Mapping(target = "scheduler", source = "scheduler.id")
    @Mapping(
            target = "participants",
            source = "participants",
            qualifiedByName = "toParticipantEntities"
    )
    @Mapping(
        target = "publishedAppointmentDate",
        source = "appointmentDate",
        qualifiedByName = "toAppointmentDateEntity"
    )
    AppointmentEntity toAppointmentEntity(
            Appointment appointment,
            //TODO prüfen ob es möglich ist anders zu implementieren
            @Context Appointment appointmentContext
    );

    @Named("toParticipantEntities")
    default Collection<AppointmentParticipantEntity> toParticipantEntities(
            Collection<PersonId> personIds,
            //TODO prüfen ob es möglich ist anders zu implementieren
            @Context Appointment appointment
    ) {
        return personIds.stream()
                //TODO prüfen ob nur eine Id übergabe reicht oder ob hier noch mehr infos gebraucht werden
                .map(personId ->
                        new AppointmentParticipantEntity(
                            new AppointmentEntity(
                                appointment.getAppointmentId().id()
                            ),
                            personId.id()
                        )
                )
                .collect(Collectors.toList());
    }
}
