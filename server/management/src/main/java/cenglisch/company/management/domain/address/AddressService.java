package cenglisch.company.management.domain.address;


import cenglisch.domain.model.EventHandler;

import java.util.Optional;

public final class AddressService {

    private final AddressRepository addressRepository;

    private final EventHandler eventHandler;

    public AddressService(final AddressRepository addressRepository, final EventHandler eventHandler) {
        this.addressRepository = addressRepository;
        this.eventHandler = eventHandler;
    }

    public AddressId createOrGetAddress(
            final String street,
            final String houseNumber,
            final String zip, final String city
    ) {
        final Optional<Address> optionalAddress = addressRepository.findByStreetAndHouseNumberAndZipAndCity(
                street,
                houseNumber,
                zip,
                city
        );

        if (optionalAddress.isPresent()) {
            return optionalAddress.get().getId();
        }
        final Address address = new Address(street, houseNumber, zip, city);
        addressRepository.save(address);
        return address.getId();
    }
}
