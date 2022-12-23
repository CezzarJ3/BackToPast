package cs.julia.backtopast.exhibition.service;

import cs.julia.backtopast.exhibition.controller.dto.ExhibitionDto;
import cs.julia.backtopast.exhibition.domain.Exhibition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface ExhibitionService {
    void createExhibition(Exhibition exhibition);
    Collection<Exhibition> finaAllByName(String name);
    void deleteExhibition(Integer id);
    void updateExhibition(Integer id, String newOrganizer);
    Page<Exhibition> findPaginated(Pageable pageable);
    Exhibition findById(Integer id);
}
