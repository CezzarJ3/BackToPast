package cs.julia.backtopast.exhibitionpart.service;

import cs.julia.backtopast.exhibitionpart.controller.dto.ExhibitionPartDto;
import cs.julia.backtopast.exhibitionpart.domain.ExhibitionPart;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ExhibitionPartService {
    void createExhibitionPart(ExhibitionPartDto exhibitionPartDto);

    void deleteExhibitionPart(int exhibitionPartId);

    Collection<ExhibitionPart> findExhibitionPartByExhibitId(int exhibitId);

    Collection<ExhibitionPart> findExhibitionPartByExhibitionId(int exhibitionId);

    ExhibitionPart findById(int id);
}
