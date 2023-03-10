package cenglisch.company.managment.domain.address;

import cenglisch.company.managment.domain.Repository;

import java.util.Optional;

public interface AddressRepository extends Repository<Address, AddressId> {
    Optional<Address> findByStreetAndHouseNumberAndZipAndCity(String street, String houseNumber, String zip, String city);
}