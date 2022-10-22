package cs.julia.backtopast.exhibit.dao;

import cs.julia.backtopast.exhibit.domain.Exhibit;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExhibitRepository extends CrudRepository<Exhibit, Integer> {
    Iterable<Exhibit> findExhibitByNameContainingIgnoreCase(String name);
}
