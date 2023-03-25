package cenglisch.company.management.application.employee;

import cenglisch.company.management.domain.employee.EmployeeService;

public final class EmployeeCommandApplicationPort {

    private final EmployeeService employeeService;

    public EmployeeCommandApplicationPort(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void hireEmployee(final HireEmployee hireEmployee) {
        employeeService.hireEmployee(hireEmployee.employeeId(), hireEmployee.companyId());
    }

    public void dismissEmployee(final DismissEmployee dismissEmployee) {
        employeeService.dismissEmployee(dismissEmployee.employeeId());
    }
}
