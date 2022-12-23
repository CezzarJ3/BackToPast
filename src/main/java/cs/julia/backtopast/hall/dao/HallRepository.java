package cs.julia.backtopast.hall.dao;

import cs.julia.backtopast.hall.domain.Hall;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallRepository extends CrudRepository<Hall, Integer> {
    Iterable<Hall> findHallsByDepartmentId(int departmentId);
}
