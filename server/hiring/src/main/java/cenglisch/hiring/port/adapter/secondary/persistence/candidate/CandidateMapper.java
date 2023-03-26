package cenglisch.hiring.port.adapter.secondary.persistence.candidate;

import cenglisch.hiring.domain.model.candidate.Candidate;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CandidateMapper {
    @Mapping(source = "id", target = "candidateId.id")
    @Mapping(source = "personId", target = "personId.id")
    @Mapping(source = "jobId", target = "jobId.id")
    @Named("mapToCandidate")
    Candidate mapToCandidate(CandidateEntity candidateEntity);
    @Mapping(source = "candidateId.id", target = "id")
    @Mapping(source = "personId.id", target = "personId")
    @Mapping(source = "jobId.id", target = "jobId")
    CandidateEntity mapToCandidateEntity(Candidate candidate);

    @IterableMapping(qualifiedByName = "mapToCandidate")
    List<Candidate> mapToCandidateList(List<CandidateEntity> candidateEntities);
}
