package cenglisch.company.management.domain.address;

public final class Address {
    private AddressId id;
    private String street;
    private String houseNumber;
    private String zip;
    private String city;

    public Address(final String street, final String houseNumber, final String zip, final String city) {

    }

    public AddressId getId() {
        return id;
    }
}
