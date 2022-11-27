package cs.julia.backtopast.exhibition.dao;

import cs.julia.backtopast.exhibit.domain.Exhibit;
import cs.julia.backtopast.exhibition.domain.Exhibition;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

public interface ExhibitionRepository extends PagingAndSortingRepository<Exhibition, Integer> {
    Collection<Exhibition> findAllByNameContainingIgnoreCase(String name);
}
