package cenglisch.appointment.port.adapter.secondary.persistence.commitment;

import cenglisch.appointment.domain.model.appointment.AppointmentId;
import cenglisch.appointment.domain.model.commitment.Commitment;
import cenglisch.appointment.domain.model.commitment.CommitmentId;
import cenglisch.appointment.domain.model.commitment.CommitmentRepository;
import cenglisch.appointment.port.adapter.secondary.persistence.appointment.AppointmentJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public final class CommitmentRepositoryAdapter implements CommitmentRepository {

    private final CommitmentJpaRepository commitmentJpaRepository;

    private final AppointmentJpaRepository appointmentJpaRepository;

    private final CommitmentMapper commitmentMapper;

    public CommitmentRepositoryAdapter(
        final CommitmentJpaRepository commitmentJpaRepository,
        final AppointmentJpaRepository appointmentJpaRepository,
        final CommitmentMapper commitmentMapper
    ) {
        this.commitmentJpaRepository = commitmentJpaRepository;
        this.appointmentJpaRepository = appointmentJpaRepository;
        this.commitmentMapper = commitmentMapper;
    }

    public Optional<Commitment> findById(final CommitmentId commitmentId) {
        Optional<CommitmentEntity> optionalCommitmentEntity = commitmentJpaRepository.findById(commitmentId.id());
        return optionalCommitmentEntity.map(commitmentEntity -> commitmentMapper.toCommitment(commitmentEntity));
    }

    @Override
    public List<Commitment> findAll() {
        return commitmentMapper.toCommitmentList(commitmentJpaRepository.findAll());
    }

    @Override
    public Optional<Commitment> find(final CommitmentId id) {
        return Optional.empty();
    }

    public Commitment save(final Commitment commitment) {
        if (commitment.getCommitmentId() == null) {
            commitment.setCommitmentId(new CommitmentId(generateId()));
        }
        commitmentJpaRepository.save(commitmentMapper.toCommitmentEntity(commitment, appointmentJpaRepository));
        return commitment;
    }

    @Override
    public void remove(final Commitment commitment) {
        throw new RuntimeException("not implemented");
    }

    public Collection<Commitment> findByAppointmentId(final AppointmentId appointmentId) {
        return commitmentMapper.toCommitmentCollection(commitmentJpaRepository.findByAppointmentId(appointmentId.id()));
    }
}
