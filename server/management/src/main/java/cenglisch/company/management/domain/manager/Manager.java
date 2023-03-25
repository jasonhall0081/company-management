package cenglisch.company.management.domain.manager;

import cenglisch.company.management.domain.company.CompanyId;

import java.util.List;

@org.jmolecules.ddd.annotation.AggregateRoot
public final class Manager {
    private ManagerId managerId;

    private String name;

    private List<CompanyId> company;

    public Manager(final String name) {
        setName(name);
    }

    public void joinCompany(final CompanyId companyId) {
        company.add(companyId);
    }

    public void leaveCompany(final CompanyId companyId) {
        company.remove(companyId);
    }

    private void setManagerId(final ManagerId managerId) {
        this.managerId = managerId;
    }

    private void setName(final String name) {
        this.name = name;
    }

    public ManagerId getManagerId() {
        return managerId;
    }

    public String getName() {
        return name;
    }

    public List<CompanyId> getCompany() {
        return company;
    }
}
