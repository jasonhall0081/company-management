package cenglisch.appointment.port.adapter.secondary.persistence.appointment;

import cenglisch.appointment.domain.model.appointment.Appointment;
import cenglisch.appointment.port.adapter.secondary.persistence.appointment.date.AppointmentDateMapper;
import org.mapstruct.*;

import java.util.List;

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
            Appointment appointment
    );
}
