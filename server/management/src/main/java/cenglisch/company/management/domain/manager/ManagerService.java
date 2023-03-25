package cenglisch.company.management.domain.manager;

import cenglisch.company.management.domain.company.CompanyId;
import cenglisch.company.management.domain.manager.event.ManagerCreated;
import cenglisch.company.management.domain.manager.event.ManagerEvent;
import cenglisch.company.management.domain.manager.event.ManagerJoinedCompany;
import cenglisch.company.management.domain.manager.event.ManagerLeftCompany;
import cenglisch.domain.model.EventHandler;

import java.util.Optional;
import java.util.function.Consumer;

@org.jmolecules.ddd.annotation.Service
public final class ManagerService {
    private final ManagerRepository managerRepository;
    private final EventHandler eventHandler;

    public ManagerService(final ManagerRepository managerRepository, final EventHandler eventHandler) {
        this.managerRepository = managerRepository;
        this.eventHandler = eventHandler;
    }

    private Optional<Manager> find(final ManagerId managerId) {
        return managerRepository.find(managerId);
    }

    public void newManager(final String name) {
        Manager manager = this.managerRepository.save(new Manager(name));
        this.eventHandler.publish(new ManagerCreated(manager.getManagerId()));
    }

    public void joinCompany(final ManagerId managerId, final CompanyId companyId) {
        manageManager(
                managerId,
                manager -> manager.joinCompany(companyId),
                new ManagerJoinedCompany(managerId, companyId)
        );
    }

    public void leaveCompany(final ManagerId managerId, final CompanyId companyId) {
        manageManager(
                managerId,
                manager -> manager.leaveCompany(companyId),
                new ManagerLeftCompany(managerId, companyId)
        );
    }

    private void manageManager(
            final ManagerId managerId,
            final Consumer<Manager> action,
            final ManagerEvent managerEvent
    ) {
        Manager manager = find(managerId).orElseThrow(ManagerNotFoundException::new);
        action.accept(manager);
        managerRepository.save(manager);
        eventHandler.publish(managerEvent);
    }
}
