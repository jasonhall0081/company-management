package cenglisch.hiring.port.adapter.secondary.persistence.job;

import cenglisch.domain.model.PersonId;
import cenglisch.hiring.domain.model.job.Job;
import cenglisch.hiring.domain.model.job.ResponsibleEmployee;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface JobMapper {
    @Mapping(source = "id", target = "jobId.id")
    @Mapping(source = "responsibleEmployees", target = "responsibleEmployees", qualifiedByName = "mapPersonEntityToResponsibleEmployee")
    Job mapToJob(JobEntity jobEntity);

    @Mapping(source = "jobId.id", target = "id")
    @Mapping(source = "responsibleEmployees", target = "responsibleEmployees")
    JobEntity mapToJobEntity(Job job);

    @Named("mapPersonEntityToResponsibleEmployee")
    default List<ResponsibleEmployee> mapPersonEntityToResponsibleEmployee(Collection<PersonId> personEntities) {
        return personEntities.stream()
                .map(PersonId::getId)
                .map(ResponsibleEmployee::new)
                .collect(Collectors.toList());
    }
}