package cenglisch.company.management.domain.employee;

import cenglisch.company.management.domain.address.AddressId;
import cenglisch.company.management.domain.company.CompanyId;
import cenglisch.company.management.domain.employee.events.EmployeeCreated;
import cenglisch.company.management.domain.employee.events.EmployeeDismissed;
import cenglisch.company.management.domain.employee.events.EmployeeEvent;
import cenglisch.company.management.domain.employee.events.EmployeeHired;
import cenglisch.domain.model.EventHandler;

import java.util.Optional;
import java.util.function.Consumer;

@org.jmolecules.ddd.annotation.Service
public final class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EventHandler eventHandler;

    public EmployeeService(
            final EmployeeRepository employeeRepository,
            final EventHandler eventHandler
    ) {
        this.employeeRepository = employeeRepository;
        this.eventHandler = eventHandler;
    }

    private Optional<Employee> find(final EmployeeId employeeId) {
        return employeeRepository.find(employeeId);
    }

    public void newEmployee(final AddressId addressId) {
        Employee employee = employeeRepository.save(new Employee());
        eventHandler.publish(new EmployeeCreated(employee.getId()));
    }

    public void hireEmployee(final EmployeeId employeeId, final CompanyId companyId) {
        manageEmployee(
                employeeId,
                employee -> employee.hireEmployee(companyId),
                new EmployeeHired(employeeId, companyId)
        );
    }

    public void dismissEmployee(final EmployeeId employeeId) {
        manageEmployee(
                employeeId,
                Employee::dismissEmployee,
                new EmployeeDismissed(employeeId)
        );
    }

    private void manageEmployee(
            final EmployeeId employeeId,
            final Consumer<Employee> action,
            final EmployeeEvent employeeEvent
    ) {
        Employee employee = find(employeeId).orElseThrow(EmployeeNotFoundException::new);
        action.accept(employee);
        employeeRepository.save(employee);
        eventHandler.publish(employeeEvent);
    }
}
