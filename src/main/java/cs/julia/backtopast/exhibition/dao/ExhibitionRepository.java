package cs.julia.backtopast.exhibition.dao;

import cs.julia.backtopast.exhibit.domain.Exhibit;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ExhibitionRepository extends PagingAndSortingRepository<Exhibit, Integer> {
}
