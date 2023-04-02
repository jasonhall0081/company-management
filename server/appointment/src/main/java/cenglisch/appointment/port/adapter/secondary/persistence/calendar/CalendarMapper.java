package cenglisch.appointment.port.adapter.secondary.persistence.calendar;

import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.appointment.domain.model.calendar.Calendar;
import cenglisch.appointment.port.adapter.secondary.persistence.appointment.AppointmentEntity;
import cenglisch.appointment.port.adapter.secondary.persistence.appointment.AppointmentMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {AppointmentMapper.class}
)
public interface CalendarMapper {
    @Mapping(target = "calendarId.id", source = "id")
    @Mapping(target = "participant", source = "participant", qualifiedByName = "toPersonId")
    @Mapping(target = "appointmentIds", source = "appointments", qualifiedByName = "toAppointmentIds")
    Calendar toCalendar(CalendarEntity calendarEntity);

    @IterableMapping(qualifiedByName = "toCalendar")
    List<Calendar> toCalendarList(List<CalendarEntity> calendarEntities);

    @Mapping(target = "id", source = "calendarId.id")
    @Mapping(target = "participant", source = "participant", qualifiedByName = "toPersonIdEntity")
    @Mapping(target = "appointments", source = "appointmentIds", qualifiedByName = "toAppointmentEntities")
    CalendarEntity toCalendarEntity(Calendar calendar);

    @Named("toAppointmentIds")
    default List<AppointmentId> toAppointmentIds(List<AppointmentEntity> appointmentEntities) {
        return appointmentEntities.stream().map(AppointmentEntity::getId).map(AppointmentId::new).collect(Collectors.toList());
    }

    @Named("toAppointmentEntities")
    default List<AppointmentEntity> toAppointmentEntities(List<AppointmentId> appointmentIds) {
        return appointmentIds.stream().map(AppointmentId::id).map(AppointmentEntity::new).collect(Collectors.toList());
    }
}
