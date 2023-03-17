package cenglisch.appointment.port.adapter.secondary.persistence.appointment;

import cenglisch.appointment.domain.model.appointment.Appointment;

import cenglisch.appointment.port.adapter.secondary.persistence.appointment.date.AppointmentDateMapper;

import cenglisch.domain.model.PersonId;
import org.mapstruct.*;

import java.util.Collection;
import java.util.stream.Collectors;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = AppointmentDateMapper.class
)
public interface AppointmentMapper {

    @Mapping(target = "appointmentId.id", source = "id")
    @Mapping(target = "schedulingParticipant.id", source = "schedulingParticipant")
    @Mapping(target = "participants", source = "participants")
    @Mapping(target = "appointmentDate", source = "publishedAppointmentDate", qualifiedByName = "mapToAppointmentDate")
    Appointment mapToAppointment(AppointmentEntity appointmentEntity);

    @Mapping(target = "id", source = "appointmentId.id")
    @Mapping(target = "schedulingParticipant", source = "schedulingParticipant.id")
    @Mapping(target = "participants", source = "participants")
    @Mapping(target = "publishedAppointmentDate", source = "appointmentDate", qualifiedByName = "mapToAppointmentDateEntity")
    AppointmentEntity mapToAppointmentEntity(
            Appointment appointment,
            @Context AppointmentJpaRepository appointmentJpaRepository
    );

    default Collection<PersonId> mapToParticipantCollection(Collection<String> participants) {
        return participants.stream().map(PersonId::new).collect(Collectors.toList());
    }

    default Collection<String> mapFromParticipantCollection(Collection<PersonId> personIds) {
        return personIds.stream().map(PersonId::getId).collect(Collectors.toList());
    }
}
