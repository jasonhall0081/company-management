package cenglisch.hiring.port.adapter.secondary.persistence.job;

import cenglisch.Default;

import cenglisch.hiring.port.adapter.secondary.persistence.job.responsible.employee.ResponsibleEmployeeEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "job")
public class JobEntity {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column
    private String jobName;

    @Column
    private int neededCapacities;

    @Column
    private boolean published;

    @OneToMany(mappedBy = "job")
    private List<ResponsibleEmployeeEntity> responsibleEmployees;

    public JobEntity() {}

    public JobEntity(String id) {
        this.id = id;
    }

    @Default
    public JobEntity(String id, String jobName, int neededCapacities, boolean published, List<ResponsibleEmployeeEntity> responsibleEmployees) {
        this.id = id;
        this.jobName = jobName;
        this.neededCapacities = neededCapacities;
        this.published = published;
        this.responsibleEmployees = responsibleEmployees;
    }

    public String getId() {
        return id;
    }

    public String getJobName() {
        return jobName;
    }

    public int getNeededCapacities() {
        return neededCapacities;
    }

    public boolean isPublished() {
        return published;
    }

    public List<ResponsibleEmployeeEntity> getResponsibleEmployees() {
        return responsibleEmployees;
    }
}