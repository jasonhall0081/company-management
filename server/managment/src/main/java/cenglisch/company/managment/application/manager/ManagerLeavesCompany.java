package cenglisch.company.managment.application.manager;

import cenglisch.company.managment.domain.company.CompanyId;
import cenglisch.company.managment.domain.manager.ManagerId;

public record ManagerLeavesCompany(ManagerId managerId, CompanyId companyId) {
}