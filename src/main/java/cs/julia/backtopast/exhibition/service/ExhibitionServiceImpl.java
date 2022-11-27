package cs.julia.backtopast.exhibition.service;

import cs.julia.backtopast.exhibit.domain.Exhibit;
import cs.julia.backtopast.exhibition.controller.ExhibitionController;
import cs.julia.backtopast.exhibition.controller.dto.ExhibitionDto;
import cs.julia.backtopast.exhibition.dao.ExhibitionRepository;
import cs.julia.backtopast.exhibition.domain.Exhibition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class ExhibitionServiceImpl implements ExhibitionService {
    private final ExhibitionRepository exhibitionRepository;

    @Autowired
    public ExhibitionServiceImpl(ExhibitionRepository exhibitionRepository) {
        this.exhibitionRepository = exhibitionRepository;
    }

    @Override
    public void createExhibition(ExhibitionDto exhibitionDto) {
        Exhibition exhibition = new Exhibition()
                .setName(exhibitionDto.name())
                .setStart_date((Timestamp) exhibitionDto.start_date())
                .setEnd_date((Timestamp) exhibitionDto.end_sate())
                .setCountry(exhibitionDto.country())
                .setCity(exhibitionDto.city())
                .setPlace(exhibitionDto.place())
                .setOrganizer(exhibitionDto.organizer());
        exhibitionRepository.save(exhibition);
    }

    @Override
    public Collection<Exhibition> findAllExhibitions() {
        return (Collection<Exhibition>) exhibitionRepository.findAll();
    }

    @Override
    public Collection<Exhibition> finaAllByName(String name) {
        return exhibitionRepository.findAllByNameContainingIgnoreCase(name);
    }

    @Override
    public void deleteExhibition(Integer id) {
        exhibitionRepository.deleteById(id);
    }

    @Override
    public void updateExhibition(Integer id, String newOrganizer) {

    }

    @Override
    public Page<Exhibition> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = pageSize * currentPage;
        List<Exhibition> exhibits = (List<Exhibition>) exhibitionRepository.findAll();

        List<Exhibition> list;
        if (exhibits.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, exhibits.size());
            list = exhibits.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), exhibits.size());
    }

    @Override
    public Exhibition findById(Integer id) {
        return exhibitionRepository.findById(id).get();
    }
}
