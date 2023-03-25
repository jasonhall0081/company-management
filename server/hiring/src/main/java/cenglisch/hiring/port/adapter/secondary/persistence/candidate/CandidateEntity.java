package cenglisch.hiring.port.adapter.secondary.persistence.candidate;

import cenglisch.Default;
import cenglisch.hiring.domain.model.candidate.CandidateState;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "candidate")
public final class CandidateEntity {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    private String personId;

    private String jobId;

    @Enumerated
    private CandidateState candidateState;

    public CandidateEntity() {
    }

    @Default
    public CandidateEntity(
        final String id,
        final String jobId,
        final String personId,
        final CandidateState candidateState
    ) {
        this.id = id;
        this.jobId = jobId;
        this.personId = personId;
        this.candidateState = candidateState;
    }

    public String getId() {
        return id;
    }

    public String getJobId() {
        return jobId;
    }

    public String getPersonId() {
        return personId;
    }

    public CandidateState getCandidateState() {
        return candidateState;
    }
}
