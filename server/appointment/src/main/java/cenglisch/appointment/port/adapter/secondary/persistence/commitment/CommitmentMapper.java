package cenglisch.appointment.port.adapter.secondary.persistence.commitment;

import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.appointment.domain.model.appointment.date.AppointmentDateId;
import cenglisch.appointment.domain.model.commitment.Commitment;
import cenglisch.appointment.port.adapter.secondary.persistence.appointment.AppointmentEntity;
import cenglisch.appointment.port.adapter.secondary.persistence.appointment.date.AppointmentDateEntity;
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
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface CommitmentMapper {
    @Mapping(target = "commitmentId.id", source = "id")
    @Mapping(target = "appointmentId.id", source = "appointment.id")
    @Mapping(target = "appointmentDateId.id", source = "appointmentDate.id")
    @Mapping(target = "participant.id", source = "participant")
    @Mapping(target = "commitmentGivenAt.timestamp", source = "commitmentGivenAt")
    @Named("toCommitment")
    Commitment toCommitment(CommitmentEntity commitmentEntity);

    @IterableMapping(qualifiedByName = "toCommitment")
    List<Commitment> toCommitmentList(List<CommitmentEntity> commitmentEntityList);

    @Mapping(target = "id", source = "commitmentId.id")
    @Mapping(target = "appointment", source = "appointmentId", qualifiedByName = "commitmentToAppointmentEntity")
    @Mapping(
        target = "appointmentDate",
          source = "appointmentDateId",
          qualifiedByName = "commitmentToAppointmentDateEntity"
    )
    @Mapping(target = "participant", source = "participant.id")
    @Mapping(target = "commitmentGivenAt", source = "commitmentGivenAt.timestamp")
    @Named("toCommitmentEntity")
    CommitmentEntity toCommitmentEntity(
            Commitment commitment
    );

    @IterableMapping(qualifiedByName = "toCommitmentEntity")
    Collection<CommitmentEntity> toCommitmentEntityCollection(Collection<Commitment> commitments);

    default Collection<Commitment> toCommitmentCollection(Collection<CommitmentEntity> commitmentEntityCollection) {
            return commitmentEntityCollection.stream()
                    .map(this::toCommitment)
                    .collect(Collectors.toList());
    }

    @Named("commitmentToAppointmentEntity")
    default AppointmentEntity commitmentToAppointmentEntity(AppointmentId appointmentId) {
        return new AppointmentEntity(appointmentId.id());
    }

    @Named("commitmentToAppointmentDateEntity")
    default AppointmentDateEntity commitmentToAppointmentDateEntity(AppointmentDateId appointmentDateId) {
        return new AppointmentDateEntity(appointmentDateId.id());
    }
}
