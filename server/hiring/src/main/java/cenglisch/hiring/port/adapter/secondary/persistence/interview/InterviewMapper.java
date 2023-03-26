package cenglisch.hiring.port.adapter.secondary.persistence.interview;

import cenglisch.hiring.domain.model.interview.Interview;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface InterviewMapper {

    @Mapping(source = "id", target = "interviewId.id")
    @Mapping(source = "candidateId", target = "candidateId.id")
    @Named("toInterview")
    Interview toInterview(InterviewEntity interviewEntity);

    @Mapping(source = "interviewId.id", target = "id")
    @Mapping(source = "candidateId.id", target = "candidateId")
    InterviewEntity toInterviewEntity(Interview interview);

    @IterableMapping(qualifiedByName = "toInterview")
    List<Interview> toInterviewList(List<InterviewEntity> all);
}
