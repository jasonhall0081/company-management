package cenglisch.appointment.port.adapter.secondary.persistence.appointment;

import cenglisch.appointment.domain.model.appointment.Appointment;

import cenglisch.appointment.port.adapter.secondary.persistence.appointment.date.AppointmentDateMapper;

import cenglisch.domain.model.PersonId;
import org.mapstruct.Context;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = AppointmentDateMapper.class
)
public interface AppointmentMapper {

    @Mapping(target = "appointmentId.id", source = "id")
    @Mapping(target = "schedulingParticipant.id", source = "schedulingParticipant")
    //@Mapping(target = "participants", source = "participants")
    @Mapping(
        target = "appointmentDate",
        source = "publishedAppointmentDate",
        qualifiedByName = "toAppointmentDate"
    )
    @Named("toAppointment")
    Appointment toAppointment(AppointmentEntity appointmentEntity);

    @IterableMapping(qualifiedByName = "toAppointment")
    List<Appointment> toAppointmentList(List<AppointmentEntity> appointmentEntityList);

    @Mapping(target = "id", source = "appointmentId.id")
    @Mapping(target = "schedulingParticipant", source = "schedulingParticipant.id")
    //@Mapping(target = "participants", source = "participants")
    @Mapping(
        target = "publishedAppointmentDate",
        source = "appointmentDate",
        qualifiedByName = "toAppointmentDateEntity"
    )
    AppointmentEntity toAppointmentEntity(
            Appointment appointment,
            @Context AppointmentJpaRepository appointmentJpaRepository
    );

    /*default Collection<PersonId> toParticipantCollection(Collection<String> participants) {
        return participants.stream().map(PersonId::new).collect(Collectors.toList());
    }*/

    /*default Collection<String> fromParticipantCollection(Collection<PersonId> personIds) {
        return personIds.stream().map(PersonId::id).collect(Collectors.toList());
    }*/
}
