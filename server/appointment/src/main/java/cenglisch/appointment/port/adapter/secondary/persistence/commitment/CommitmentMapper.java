package cenglisch.appointment.port.adapter.secondary.persistence.commitment;

import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.appointment.domain.model.commitment.Commitment;
import cenglisch.appointment.port.adapter.secondary.persistence.appointment.AppointmentEntity;
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
    @Mapping(target = "participant.id", source = "participant")
    @Mapping(target = "commitmentGivenAt.timestamp", source = "commitmentGivenAt")
    @Named("toCommitment")
    Commitment toCommitment(CommitmentEntity commitmentEntity);

    @IterableMapping(qualifiedByName = "toCommitment")
    List<Commitment> toCommitmentList(List<CommitmentEntity> commitmentEntityList);

    @Mapping(target = "id", source = "commitmentId.id")
    @Mapping(target = "appointment", source = "appointmentId", qualifiedByName = "mapAppointmentIdToAppointmentEntity")
    @Mapping(target = "participant", source = "participant.id")
    @Mapping(target = "commitmentGivenAt", source = "commitmentGivenAt.timestamp")
    CommitmentEntity toCommitmentEntity(
            Commitment commitment
    );

    @IterableMapping(qualifiedByName = "toCommitmentEntity")
    Collection<CommitmentEntity> toCommitmentEntityCollection(Collection<Commitment> commitments);

    @Named("mapAppointmentIdToAppointmentEntity")
    default AppointmentEntity mapAppointmentIdToAppointmentEntity(
            AppointmentId appointmentId
    ) {
        return new AppointmentEntity(appointmentId.id());
    }

    default Collection<Commitment> toCommitmentCollection(Collection<CommitmentEntity> commitmentEntityCollection) {
            return commitmentEntityCollection.stream()
                    .map(this::toCommitment)
                    .collect(Collectors.toList());
    }
}
