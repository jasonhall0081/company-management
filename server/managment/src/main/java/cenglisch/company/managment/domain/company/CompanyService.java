package cenglisch.company.managment.domain.company;

import cenglisch.company.managment.domain.EventHandler;
import cenglisch.company.managment.domain.address.AddressId;
import cenglisch.company.managment.domain.company.events.CompanyClosed;
import cenglisch.company.managment.domain.company.events.CompanyOpened;
import cenglisch.company.managment.domain.manager.ManagerId;

public class CompanyService {
    private final CompanyRepository companyRepository;
    private final EventHandler eventHandler;

    public CompanyService(
            CompanyRepository companyRepository,
            EventHandler eventHandler
    ) {
        this.companyRepository = companyRepository;
        this.eventHandler = eventHandler;
    }

    public void openCompany(ManagerId managerId, AddressId addressId, String companyName) {
        final Company company = companyRepository.save(new Company(addressId, companyName));
        eventHandler.publish(new CompanyOpened(company.getId(), managerId));
    }

    public void closeCompany(CompanyId companyId) {
        final Company company = companyRepository.findById(companyId).orElseThrow(CompanyNotFoundException::new);
        companyRepository.remove(company);
        eventHandler.publish(new CompanyClosed(companyId));
    }
}