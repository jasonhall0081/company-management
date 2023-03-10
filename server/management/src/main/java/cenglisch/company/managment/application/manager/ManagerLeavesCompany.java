package cenglisch.company.management.application.manager;

import cenglisch.company.management.domain.company.CompanyId;
import cenglisch.company.management.domain.manager.ManagerId;

public record ManagerLeavesCompany(ManagerId managerId, CompanyId companyId) {
}
