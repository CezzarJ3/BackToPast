package cs.julia.backtopast.exhibit.service;

import cs.julia.backtopast.exhibit.controller.dto.ExhibitDto;
import cs.julia.backtopast.exhibit.domain.Exhibit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface ExhibitService {
    void createExhibit(ExhibitDto exhibitDto);

    Collection<Exhibit> findExhibitsByName(String name);

    Collection<Exhibit> findExhibitsByYear(int year);

    Collection<Exhibit> findExhibitsByNameAndYear(String name, int year);

    void deleteExhibit(int exhibitId);

    void updateExhibitDescription(Integer exhibitId, String newDescription);

    Page<Exhibit> findPaginated(Pageable pageable, String name, int year);

    Exhibit findById(int id);
}
