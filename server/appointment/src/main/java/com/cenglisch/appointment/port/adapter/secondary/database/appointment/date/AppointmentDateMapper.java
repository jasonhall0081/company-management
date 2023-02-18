package com.cenglisch.appointment.port.adapter.secondary.database.appointment.date;

import com.cenglisch.appointment.domain.appointment.AppointmentId;
import com.cenglisch.appointment.domain.appointment.date.AppointmentDate;
import com.cenglisch.appointment.port.adapter.secondary.database.appointment.AppointmentEntity;
import com.cenglisch.appointment.port.adapter.secondary.database.appointment.AppointmentJpaRepository;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface AppointmentDateMapper {

    @Mapping(target = "appointmentDateId.id", source = "id")
    @Mapping(target = "appointmentId.id", source = "appointment.id")
    @Named("mapToAppointmentDate")
    AppointmentDate mapToAppointmentDate(AppointmentDateEntity appointmentDateEntity);

    @Mapping(target = "id", source = "appointmentDateId.id")
    @Mapping(target = "appointment", source = "appointmentId", qualifiedByName = "mapAppointmentIdToAppointmentEntity")
    @Named("mapToAppointmentDateEntity")
    AppointmentDateEntity mapToAppointmentDateEntity(AppointmentDate appointmentDate, @Context AppointmentJpaRepository appointmentRepository);

    @Named("mapAppointmentIdToAppointmentEntity")
    default AppointmentEntity mapAppointmentIdToAppointmentEntity(AppointmentId appointmentId, @Context AppointmentJpaRepository appointmentRepository) {
        return appointmentRepository.findById(appointmentId.getId()).orElse(null);
    }
}
