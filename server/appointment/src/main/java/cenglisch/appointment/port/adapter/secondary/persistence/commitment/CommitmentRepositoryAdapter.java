package cenglisch.appointment.port.adapter.secondary.persistence.commitment;

import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.appointment.domain.model.appointment.date.AppointmentDateId;
import cenglisch.appointment.domain.model.commitment.Commitment;
import cenglisch.appointment.domain.model.commitment.CommitmentId;
import cenglisch.appointment.domain.model.commitment.CommitmentRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public final class CommitmentRepositoryAdapter implements CommitmentRepository {

    private final CommitmentJpaRepository commitmentJpaRepository;

    private final CommitmentMapper commitmentMapper;

    public CommitmentRepositoryAdapter(
        final CommitmentJpaRepository commitmentJpaRepository,
        final CommitmentMapper commitmentMapper
    ) {
        this.commitmentJpaRepository = commitmentJpaRepository;
        this.commitmentMapper = commitmentMapper;
    }

    public Collection<Commitment> findByAppointmentId(final AppointmentId appointmentId) {
        return commitmentMapper.toCommitmentCollection(commitmentJpaRepository.findByAppointmentId(appointmentId.id()));
    }

    public Collection<Commitment> findByAppointmentIdAndAppointmentDateId(
        final AppointmentId appointmentId,
        final AppointmentDateId appointmentDateId
    ) {
        return commitmentMapper.toCommitmentCollection(
                commitmentJpaRepository.findByAppointmentIdAndAppointmentDateId(
                        appointmentId.id(),
                        appointmentDateId.id()
                )
        );
    }

    public Optional<Commitment> findById(final CommitmentId commitmentId) {
        Optional<CommitmentEntity> optionalCommitmentEntity = commitmentJpaRepository.findById(commitmentId.id());
        return optionalCommitmentEntity.map(commitmentMapper::toCommitment);
    }

    public List<Commitment> findAll() {
        return commitmentMapper.toCommitmentList(commitmentJpaRepository.findAll());
    }

    public Optional<Commitment> find(final CommitmentId id) {
        return Optional.empty();
    }

    public Commitment save(final Commitment commitment) {
        if (commitment.getCommitmentId() == null) {
            commitment.setCommitmentId(new CommitmentId(generateId()));
        }
        commitmentJpaRepository.save(commitmentMapper.toCommitmentEntity(commitment));
        return commitment;
    }

    public void saveAll(final Collection<Commitment> commitments) {
        commitmentJpaRepository.saveAll(
                commitmentMapper.toCommitmentEntityCollection(commitments)
        );
    }

    public void remove(final Commitment commitment) {
        throw new RuntimeException("not implemented");
    }
}
