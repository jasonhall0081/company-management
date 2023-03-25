package cenglisch.company.management.domain.company;

import cenglisch.company.management.domain.address.AddressId;
import cenglisch.company.management.domain.company.events.CompanyClosed;
import cenglisch.company.management.domain.company.events.CompanyOpened;
import cenglisch.company.management.domain.manager.ManagerId;
import cenglisch.domain.model.EventHandler;

@org.jmolecules.ddd.annotation.Service
public final class CompanyService {
    private final CompanyRepository companyRepository;
    private final EventHandler eventHandler;

    public CompanyService(
            final CompanyRepository companyRepository,
            final EventHandler eventHandler
    ) {
        this.companyRepository = companyRepository;
        this.eventHandler = eventHandler;
    }

    public void openCompany(final ManagerId managerId, final AddressId addressId, final String companyName) {
        final Company company = companyRepository.save(new Company(addressId, companyName));
        eventHandler.publish(new CompanyOpened(company.getId(), managerId));
    }

    public void closeCompany(final CompanyId companyId) {
        final Company company = companyRepository.find(companyId).orElseThrow(CompanyNotFoundException::new);
        companyRepository.remove(company);
        eventHandler.publish(new CompanyClosed(companyId));
    }
}
