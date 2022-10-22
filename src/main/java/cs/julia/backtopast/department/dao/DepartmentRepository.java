package cs.julia.backtopast.department.dao;

import cs.julia.backtopast.department.domain.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Integer> {
    Iterable<Department> findAllByNameContainingIgnoreCase(String name);
}
