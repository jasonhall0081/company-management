package cenglisch.hiring.domain.model.job;

import cenglisch.Default;
import cenglisch.hiring.domain.model.Entity;
import cenglisch.hiring.domain.model.job.exception.JobException;

import java.util.ArrayList;
import java.util.List;

@org.jmolecules.ddd.annotation.AggregateRoot
public final class Job extends Entity {

    private JobId jobId;

    private String jobName;

    private int neededCapacities;

    private boolean published;

    private final List<ResponsibleEmployee> responsibleEmployees;

    private Job() {
        this.responsibleEmployees = new ArrayList<>();
    }

    public Job(final String pJobName, final int pNeededCapacities) {
        this();
        setJobName(pJobName);
        setNeededCapacities(pNeededCapacities);
        setPublished(pNeededCapacities > 0);
    }

    public Job(
        final JobId jobId,
        final String jobName,
        final int neededCapacities,
        final boolean published
    ) {
        this();
        setJobId(jobId);
        setJobName(jobName);
        setNeededCapacities(neededCapacities);
        setPublished(published);
    }

    @Default
    public Job(
            final JobId jobId,
            final String jobName,
            final int neededCapacities,
            final boolean published,
            final List<ResponsibleEmployee> responsibleEmployees
    ) {
        setJobId(jobId);
        setJobName(jobName);
        setNeededCapacities(neededCapacities);
        setPublished(published);
        this.responsibleEmployees = responsibleEmployees;
    }

    public void reduceCapacities() {
        setNeededCapacities(neededCapacities - 1);
        if (!capacitiesAvailable()) {
            setPublished(false);
        }
    }

    public void publishJobPosting(final int neededCapacities) {
        if (neededCapacities < 1) {
            throw new JobException("job can not be published if needed capacities are lower than 1");
        }
        setNeededCapacities(neededCapacities);
        setPublished(true);
    }

    public void addResponsibleEmployee(final ResponsibleEmployee responsibleEmployee) {
        assertArgumentNotNull(responsibleEmployee, "responsible employee can not be null");
        this.responsibleEmployees.add(responsibleEmployee);
    }

    public void removeResponsibleEmployee(final ResponsibleEmployee responsibleEmployee) {
        assertArgumentNotNull(responsibleEmployee, "responsible employee can not be null");
        this.responsibleEmployees.remove(responsibleEmployee);
    }

    private boolean capacitiesAvailable() {
        return neededCapacities > 0;
    }

    public void setJobId(final JobId jobId) {
        assertArgumentNotNull(jobId, "job id can not be null");
        this.jobId = jobId;
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    private void setJobName(final String jobName) {
        assertArgumentNotNull(jobName, "job name can not be null");
        assertArgumentLength(
                jobName,
                3,
                30,
                "job name must be at least 1 and maximum 30 characters long"
        );
        this.jobName = jobName;
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    private void setNeededCapacities(final int neededCapacities) {
        assertArgumentNotNull(neededCapacities, "need capacities can not be null");
        assertArgumentRange(
                neededCapacities,
                0,
                99,
                "needed capacities minimum is 0 or maximum 99"
        );
        this.neededCapacities = neededCapacities;
    }

    private void setPublished(final boolean published) {
        assertArgumentNotNull(published, "Published can not be null");
        this.published = published;
    }

    public JobId getJobId() {
        return jobId;
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

    public List<ResponsibleEmployee> getResponsibleEmployees() {
        return responsibleEmployees;
    }
}
