package cenglisch.appointment.port.adapter.secondary.persistence.appointment.interview;

import cenglisch.appointment.domain.model.appointment.interview.AppointmentInterview;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface AppointmentInterviewMapper {
    @Mapping(target = "id", source = "appointmentInterviewId.id")
    @Mapping(target = "appointmentId", source = "appointmentId.id")
    AppointmentInterviewEntity toAppointmentInterviewEntity(AppointmentInterview appointmentInterview);

    @Mapping(target = "appointmentInterviewId.id", source = "id")
    @Mapping(target = "appointmentId.id", source = "appointmentId")
    @Named("toAppointmentInterview")
    AppointmentInterview toAppointmentInterview(AppointmentInterviewEntity appointmentInterviewEntity);

    @IterableMapping(qualifiedByName = "toAppointmentInterview")
    List<AppointmentInterview> toAppointmentInterviewList(
            List<AppointmentInterviewEntity> appointmentInterviewEntityList
    );
}
