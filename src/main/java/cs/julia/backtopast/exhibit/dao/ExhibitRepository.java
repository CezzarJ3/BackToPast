package cs.julia.backtopast.exhibit.dao;

import cs.julia.backtopast.exhibit.domain.Exhibit;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExhibitRepository extends PagingAndSortingRepository<Exhibit, Integer> {
    Iterable<Exhibit> findExhibitByNameContainingIgnoreCase(String name);

    Iterable<Exhibit> findExhibitByYear(int year);

    Iterable<Exhibit> findExhibitByNameContainingIgnoreCaseAndYear(String name, int year);
}
