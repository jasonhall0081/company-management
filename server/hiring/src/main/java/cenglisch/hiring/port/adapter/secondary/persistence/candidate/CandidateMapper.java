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
    @Named("toCandidate")
    Candidate toCandidate(CandidateEntity candidateEntity);
    @Mapping(source = "candidateId.id", target = "id")
    @Mapping(source = "personId.id", target = "personId")
    @Mapping(source = "jobId.id", target = "jobId")
    CandidateEntity toCandidateEntity(Candidate candidate);

    @IterableMapping(qualifiedByName = "toCandidate")
    List<Candidate> toCandidateList(List<CandidateEntity> candidateEntities);
}
