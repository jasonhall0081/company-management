package cenglisch.hiring.domain.model.job;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public class JobId {

    private String id;

    public JobId(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
