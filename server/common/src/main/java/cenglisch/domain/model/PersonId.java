package cenglisch.domain.model;


/**
 * This Identity provides value from sso user
 */
public class PersonId {

    private final String id;

    public PersonId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
