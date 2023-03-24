package cenglisch.hiring.domain.model.interview;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public class InterviewId {

    private String id;

    public InterviewId(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
