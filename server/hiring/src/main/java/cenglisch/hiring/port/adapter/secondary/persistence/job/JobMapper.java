package cenglisch.hiring.port.adapter.secondary.persistence.job;

import cenglisch.domain.model.PersonId;
import cenglisch.hiring.domain.model.job.Job;
import cenglisch.hiring.domain.model.job.ResponsibleEmployee;
import cenglisch.hiring.port.adapter.secondary.persistence.job.responsible.employee.ResponsibleEmployeeEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface JobMapper {
    @Mapping(source = "id", target = "jobId.id")
    @Mapping(
        source = "responsibleEmployees",
        target = "responsibleEmployees",
        qualifiedByName = "mapToResponsibleEmployee"
    )
    @Named("mapToJob")
    Job mapToJob(JobEntity jobEntity);

    @Mapping(source = "jobId.id", target = "id")
    @Mapping(
        source = "responsibleEmployees",
        target = "responsibleEmployees",
        qualifiedByName = "mapToResponsibleEmployeeEntity"
    )
    JobEntity mapToJobEntity(Job job);

    @IterableMapping(qualifiedByName = "mapToJob")
    List<Job> mapToJobList(List<JobEntity> all);

    @Named("mapToResponsibleEmployee")
    default List<ResponsibleEmployee> mapToResponsibleEmployee(
            List<ResponsibleEmployeeEntity> responsibleEmployeeEntities
    ) {
        List<ResponsibleEmployee> responsibleEmployees = new ArrayList<>();
        for (ResponsibleEmployeeEntity entity : responsibleEmployeeEntities) {
            ResponsibleEmployee responsibleEmployee = new ResponsibleEmployee(new PersonId(entity.getPersonId()));
            responsibleEmployees.add(responsibleEmployee);
        }
        return responsibleEmployees;
    }

    @Named("mapToResponsibleEmployeeEntity")
    default List<ResponsibleEmployeeEntity> mapToResponsibleEmployeeEntity(
            List<ResponsibleEmployee> responsibleEmployees
    ) {
        List<ResponsibleEmployeeEntity> responsibleEmployeeEntities = new ArrayList<>();
        for (ResponsibleEmployee responsibleEmployee : responsibleEmployees) {
            ResponsibleEmployeeEntity responsibleEmployeeEntity = new ResponsibleEmployeeEntity(
                    responsibleEmployee.getPerson().id(),
                    null
                    //new JobEntity(job.getJobId().getId())
            );
            responsibleEmployeeEntities.add(responsibleEmployeeEntity);
        }
        return responsibleEmployeeEntities;
    }
}
