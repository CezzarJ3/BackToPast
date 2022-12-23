package cs.julia.backtopast.hall.service;

import cs.julia.backtopast.department.dao.DepartmentRepository;
import cs.julia.backtopast.department.domain.Department;
import cs.julia.backtopast.hall.controller.Dto.HallDto;
import cs.julia.backtopast.hall.dao.HallRepository;
import cs.julia.backtopast.hall.domain.Hall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Service
public class HallServiceImpl implements HallService {
    private final HallRepository hallRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public HallServiceImpl(HallRepository hallRepository, DepartmentRepository departmentRepository) {
        this.hallRepository = hallRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void createHall(HallDto hallDto) {
        Department department = departmentRepository.findById(hallDto.type())
                .orElseThrow(() -> new EntityNotFoundException("Отдел #[%s] не найден".formatted(hallDto.type())));

        Hall hall = new Hall()
                .setNumber(hallDto.number())
                .setDepartment(department);
        hallRepository.save(hall);
    }

    @Override
    public Collection<Hall> findHallsByDepartment(int departmentId) {
        return (Collection<Hall>) hallRepository.findHallsByDepartmentId(departmentId);
    }

    @Override
    public void deleteHall(int hallId) {
        hallRepository.deleteById(hallId);
    }

    @Override
    public Hall findById(int id) {
        return hallRepository.findById(id).get();
    }
}
