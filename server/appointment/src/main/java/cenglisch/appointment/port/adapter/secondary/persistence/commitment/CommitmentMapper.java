package cenglisch.appointment.port.adapter.secondary.persistence.commitment;

import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.appointment.domain.model.commitment.Commitment;
import cenglisch.appointment.port.adapter.secondary.persistence.appointment.AppointmentEntity;
import cenglisch.appointment.port.adapter.secondary.persistence.appointment.AppointmentJpaRepository;
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
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface CommitmentMapper {
    @Mapping(target = "commitmentId.id", source = "id")
    @Mapping(target = "appointmentId.id", source = "appointment.id")
    @Mapping(target = "participant.id", source = "participant")
    @Named("toCommitment")
    Commitment toCommitment(CommitmentEntity commitmentEntity);

    @IterableMapping(qualifiedByName = "toCommitment")
    List<Commitment> toCommitmentList(List<CommitmentEntity> commitmentEntityList);

    @Mapping(target = "id", source = "commitmentId.id")
    @Mapping(target = "appointment", source = "appointmentId", qualifiedByName = "mapAppointmentIdToAppointmentEntity")
    @Mapping(target = "participant", source = "participant.id")
    CommitmentEntity toCommitmentEntity(
            Commitment commitment,
            @Context AppointmentJpaRepository appointmentRepository
    );

    @Named("mapAppointmentIdToAppointmentEntity")
    default AppointmentEntity mapAppointmentIdToAppointmentEntity(
            AppointmentId appointmentId,
            @Context AppointmentJpaRepository appointmentRepository
    ) {
        return appointmentRepository.findById(appointmentId.id()).orElse(null);
    }

    default Collection<Commitment> toCommitmentCollection(Collection<CommitmentEntity> commitmentEntityCollection) {
            return commitmentEntityCollection.stream()
                    .map(this::toCommitment)
                    .collect(Collectors.toList());
    }
}
