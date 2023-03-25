package cenglisch.hiring.domain.model.job;

import cenglisch.domain.model.PersonId;

@org.jmolecules.ddd.annotation.ValueObject
public final class ResponsibleEmployee {
    private final PersonId person;

    public ResponsibleEmployee(final PersonId personId) {
        this.person = personId;
    }

    public PersonId getPerson() {
        return person;
    }
}
