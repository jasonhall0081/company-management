package cenglisch.hiring.domain.model.job;

import cenglisch.domain.model.PersonId;

public class ResponsibleEmployee {
    private final PersonId person;

    public ResponsibleEmployee(PersonId personId) {
        this.person = personId;
    }

    public PersonId getPerson() {
        return person;
    }
}
