package cenglisch.hiring.domain.model.job;

import cenglisch.domain.model.PersonId;
import org.jmolecules.ddd.annotation.ValueObject;


@ValueObject
public class ResponsibleEmployee {
    private final PersonId person;

    public ResponsibleEmployee(PersonId personId) {
        this.person = personId;
    }

    public PersonId getPerson() {
        return person;
    }
}
