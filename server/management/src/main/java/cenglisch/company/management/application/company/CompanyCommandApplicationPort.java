package cenglisch.company.management.application.company;

import cenglisch.company.management.domain.address.AddressService;
import cenglisch.company.management.domain.company.CompanyService;

public final class CompanyCommandApplicationPort {

    private final CompanyService companyService;
    private final AddressService addressService;

    public CompanyCommandApplicationPort(final CompanyService companyService, final AddressService addressService) {
        this.companyService = companyService;
        this.addressService = addressService;
    }

    public void openCompany(final OpenCompany openCompany) {
        companyService.openCompany(
                openCompany.managerId(),
                addressService.createOrGetAddress(
                        openCompany.street(),
                        openCompany.houseNumber(),
                        openCompany.zip(),
                        openCompany.city()
                ),
                openCompany.companyName()
        );
    }

    public void closeCompany(final CloseCompany closeCompany) {
        companyService.closeCompany(closeCompany.companyId());
    }
}
