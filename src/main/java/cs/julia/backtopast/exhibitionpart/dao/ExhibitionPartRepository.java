package cs.julia.backtopast.exhibitionpart.dao;

import cs.julia.backtopast.exhibitionpart.domain.ExhibitionPart;
import org.springframework.data.repository.CrudRepository;

public interface ExhibitionPartRepository extends CrudRepository<ExhibitionPart, Integer> {
    Iterable<ExhibitionPart> findExhibitionPartsByExhibitId(int exhibitId);

    Iterable<ExhibitionPart> findExhibitionPartsByExhibitionId(int exhibitionId);
}
