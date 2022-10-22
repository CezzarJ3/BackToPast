package cs.julia.backtopast.department.service;

import cs.julia.backtopast.department.domain.Department;

import java.util.Collection;

public interface DepartmentService {
    void create(Department department);

    Collection<Department> findAllByName(String name);

    Collection<Department>  findAll();
}
