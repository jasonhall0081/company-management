package cenglisch.hiring.port.adapter.secondary.persistence.job.responsible.employee;

import cenglisch.Default;
import cenglisch.hiring.port.adapter.secondary.persistence.job.JobEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "responsible_employee")
public class ResponsibleEmployeeEntity {
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
    public ResponsibleEmployeeEntity(String personId, JobEntity job) {
        this.personId = personId;
        this.job = job;
    }

    public ResponsibleEmployeeEntity(String id, String personId, JobEntity job) {
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
