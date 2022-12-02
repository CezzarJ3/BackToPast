package cs.julia.backtopast.department.service;

import cs.julia.backtopast.department.domain.Department;

import java.util.Collection;

public interface DepartmentService {
    void create(Department department);

    void delete(Integer id);

    Collection<Department> findAllByName(String name);

    Collection<Department> findAll();
}
