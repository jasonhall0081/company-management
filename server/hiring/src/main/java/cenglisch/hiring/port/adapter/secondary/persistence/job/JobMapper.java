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
        qualifiedByName = "toResponsibleEmployee"
    )
    @Named("toJob")
    Job toJob(JobEntity jobEntity);

    @Mapping(source = "jobId.id", target = "id")
    @Mapping(
        source = "responsibleEmployees",
        target = "responsibleEmployees",
        qualifiedByName = "toResponsibleEmployeeEntity"
    )
    JobEntity toJobEntity(Job job);

    @IterableMapping(qualifiedByName = "toJob")
    List<Job> toJobList(List<JobEntity> all);

    @Named("toResponsibleEmployee")
    default List<ResponsibleEmployee> toResponsibleEmployee(
            List<ResponsibleEmployeeEntity> responsibleEmployeeEntities
    ) {
        List<ResponsibleEmployee> responsibleEmployees = new ArrayList<>();
        for (ResponsibleEmployeeEntity entity : responsibleEmployeeEntities) {
            ResponsibleEmployee responsibleEmployee = new ResponsibleEmployee(new PersonId(entity.getPersonId()));
            responsibleEmployees.add(responsibleEmployee);
        }
        return responsibleEmployees;
    }

    @Named("toResponsibleEmployeeEntity")
    default List<ResponsibleEmployeeEntity> toResponsibleEmployeeEntity(
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
