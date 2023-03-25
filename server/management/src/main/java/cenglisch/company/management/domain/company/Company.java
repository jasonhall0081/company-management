package cenglisch.company.management.domain.company;

import cenglisch.company.management.domain.address.AddressId;

@org.jmolecules.ddd.annotation.AggregateRoot
public final class Company {

    private CompanyId id;

    private AddressId addressId;
    private String name;


    public Company(final AddressId addressId, final String name) {
        setAddressId(addressId);
        setName(name);
    }

    private void setAddressId(final AddressId addressId) {
        this.addressId = addressId;
    }

    private void setName(final String name) {
        this.name = name;
    }

    public CompanyId getId() {
        return id;
    }
}
