package cenglisch.hiring.port.adapter.secondary.persistence.job.responsible.employee;

import cenglisch.Default;
import cenglisch.hiring.port.adapter.secondary.persistence.job.JobEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "responsible_employee")
public final class ResponsibleEmployeeEntity {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "person_id")
    private String personId;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private JobEntity job;

    public ResponsibleEmployeeEntity() {

    }

    @Default
    public ResponsibleEmployeeEntity(final String personId, final JobEntity job) {
        this.personId = personId;
        this.job = job;
    }

    public ResponsibleEmployeeEntity(final String id, final String personId, final JobEntity job) {
        this.id = id;
        this.personId = personId;
        this.job = job;
    }

    public String getId() {
        return id;
    }

    public String getPersonId() {
        return personId;
    }

    public JobEntity getJob() {
        return job;
    }
}
