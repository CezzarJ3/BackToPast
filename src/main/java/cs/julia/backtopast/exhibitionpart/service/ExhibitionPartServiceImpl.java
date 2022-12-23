package cs.julia.backtopast.exhibitionpart.service;

import cs.julia.backtopast.exhibit.dao.ExhibitRepository;
import cs.julia.backtopast.exhibit.domain.Exhibit;
import cs.julia.backtopast.exhibition.dao.ExhibitionRepository;
import cs.julia.backtopast.exhibition.domain.Exhibition;
import cs.julia.backtopast.exhibitionpart.controller.dto.ExhibitionPartDto;
import cs.julia.backtopast.exhibitionpart.dao.ExhibitionPartRepository;
import cs.julia.backtopast.exhibitionpart.domain.ExhibitionPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Service
public class ExhibitionPartServiceImpl implements ExhibitionPartService {

    private final ExhibitionPartRepository exhibitionPartRepository;
    private final ExhibitRepository exhibitRepository;
    private final ExhibitionRepository exhibitionRepository;

    @Autowired
    public ExhibitionPartServiceImpl(ExhibitRepository exhibitRepository, ExhibitionRepository exhibitionRepository, ExhibitionPartRepository exhibitionPartRepository) {
        this.exhibitRepository = exhibitRepository;
        this.exhibitionRepository = exhibitionRepository;
        this.exhibitionPartRepository = exhibitionPartRepository;
    }

    @Override
    public void createExhibitionPart(ExhibitionPartDto exhibitionPartDto) {
        Exhibit exhibit = exhibitRepository.findById(exhibitionPartDto.exhibitId())
                .orElseThrow(() -> new EntityNotFoundException("Экспонат #[%s] не найден".formatted(exhibitionPartDto.exhibitId())));
        Exhibition exhibition = exhibitionRepository.findById(exhibitionPartDto.exhibitionId())
                .orElseThrow(() -> new EntityNotFoundException("Выставка #[%s] не найдена".formatted(exhibitionPartDto.exhibitionId())));

        ExhibitionPart exhibitionPart = new ExhibitionPart()
                .setExhibit(exhibit)
                .setExhibition(exhibition);

        exhibitionPartRepository.save(exhibitionPart);
    }

    @Override
    public void deleteExhibitionPart(int exhibitionPartId) {
        exhibitionPartRepository.deleteById(exhibitionPartId);
    }

    @Override
    public Collection<ExhibitionPart> findExhibitionPartByExhibitId(int exhibitId) {
        return (Collection<ExhibitionPart>) exhibitionPartRepository.findExhibitionPartsByExhibitId(exhibitId);
    }

    @Override
    public Collection<ExhibitionPart> findExhibitionPartByExhibitionId(int exhibitionId) {
        return (Collection<ExhibitionPart>) exhibitionPartRepository.findExhibitionPartsByExhibitionId(exhibitionId);
    }

    @Override
    public ExhibitionPart findById(int id) {
        return exhibitionPartRepository.findById(id).get();
    }
}
