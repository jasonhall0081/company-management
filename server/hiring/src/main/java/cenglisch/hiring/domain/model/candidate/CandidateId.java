package cenglisch.hiring.domain.model.candidate;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public class CandidateId {
    private String id;

    public CandidateId(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
