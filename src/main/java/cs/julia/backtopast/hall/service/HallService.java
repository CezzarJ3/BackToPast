package cs.julia.backtopast.hall.service;

import cs.julia.backtopast.hall.controller.Dto.HallDto;
import cs.julia.backtopast.hall.domain.Hall;

import java.util.Collection;

public interface HallService {
    void createHall(HallDto hallDto);

    Collection<Hall> findHallsByDepartment(int departmentId);

    void deleteHall(int hallId);

    Hall findById(int id);
}
