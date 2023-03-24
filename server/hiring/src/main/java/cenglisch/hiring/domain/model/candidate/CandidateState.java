package cenglisch.hiring.domain.model.candidate;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public enum CandidateState {
    APPLICATION_ACCEPTED,
    APPLICATION_APPROVED,
    APPLICATION_REJECTED,
    ADOPTED,
    REJECTED
}
