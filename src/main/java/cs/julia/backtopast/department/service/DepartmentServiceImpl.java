package cs.julia.backtopast.department.service;

import cs.julia.backtopast.department.dao.DepartmentRepository;
import cs.julia.backtopast.department.domain.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Transactional
    public void create(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public void delete(Integer id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public Collection<Department> findAllByName(String name) {
        return (Collection<Department>) departmentRepository.findAllByNameContainingIgnoreCase(name);
    }

    @Override
    public Collection<Department> findAll() {
        return (Collection<Department>) departmentRepository.findAll();
    }
}
