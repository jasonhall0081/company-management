package cenglisch.company.management.domain.department;

import cenglisch.domain.model.Repository;

import java.util.Optional;

public interface DepartmentRepository extends Repository<Department, DepartmentId> {
    Optional<Department> findByName(String name);
}
