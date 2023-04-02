package cenglisch.appointment.port.adapter.secondary.persistence.appointment.date;

import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.appointment.domain.model.appointment.date.AppointmentDate;
import cenglisch.appointment.port.adapter.secondary.persistence.appointment.AppointmentEntity;
import cenglisch.appointment.port.adapter.secondary.persistence.appointment.AppointmentJpaRepository;
import org.mapstruct.Context;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface AppointmentDateMapper {

    @Mapping(target = "appointmentDateId.id", source = "id")
    @Mapping(target = "appointmentId.id", source = "appointment.id")
    @Named("toAppointmentDate")
    AppointmentDate toAppointmentDate(AppointmentDateEntity appointmentDateEntity);

    @Mapping(target = "id", source = "appointmentDateId.id")
    @Mapping(
        target = "appointment",
        source = "appointmentId",
        qualifiedByName = "fromAppointmentDateToAppointmentEntity"
    )
    @Named("toAppointmentDateEntity")
    AppointmentDateEntity toAppointmentDateEntity(
            AppointmentDate appointmentDate
    );

    @Named("fromAppointmentDateToAppointmentEntity")
    default AppointmentEntity fromAppointmentDateToAppointmentEntity(AppointmentId appointmentId){
        //TODO prüfen ob die Methode gegen ein direktes Mapping entfernt werden kann
        return new AppointmentEntity(appointmentId.id());
    }
}
