package cenglisch.company.management.domain.employee;

import cenglisch.company.management.domain.address.AddressId;
import cenglisch.company.management.domain.company.CompanyId;
import cenglisch.company.management.domain.department.DepartmentId;

import java.util.Date;

public final class Employee {
    private EmployeeId id;

    private AddressId address;

    private CompanyId company;

    private DepartmentId department;

    private String firstname;

    private String lastname;

    private Date birthday;

    public void hireEmployee(final CompanyId companyId) {
        setCompany(companyId);
    }

    public void dismissEmployee() {
        company = null;
        department = null;
    }

    private void setId(final EmployeeId id) {
        this.id = id;
    }

    private void setAddress(final AddressId address) {
        this.address = address;
    }

    private void setCompany(final CompanyId company) {
        this.company = company;
    }

    private void setDepartment(final DepartmentId department) {
        this.department = department;
    }

    private void setFirstname(final String firstname) {
        this.firstname = firstname;
    }

    private void setLastname(final String lastname) {
        this.lastname = lastname;
    }

    private void setBirthday(final Date birthday) {
        this.birthday = birthday;
    }

    public EmployeeId getId() {
        return id;
    }

    public AddressId getAddress() {
        return address;
    }

    public CompanyId getCompany() {
        return company;
    }

    public DepartmentId getDepartment() {
        return department;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Date getBirthday() {
        return birthday;
    }
}
