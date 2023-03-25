package cenglisch.hiring.port.adapter.secondary.persistence.job;

import cenglisch.Default;

import cenglisch.hiring.port.adapter.secondary.persistence.job.responsible.employee.ResponsibleEmployeeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "job")
public final class JobEntity {
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

    public JobEntity() {

    }

    public JobEntity(final String id) {
        this.id = id;
    }

    @Default
    public JobEntity(
        final String id,
        final String jobName,
        final int neededCapacities,
        final boolean published,
        final List<ResponsibleEmployeeEntity> responsibleEmployees
    ) {
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
