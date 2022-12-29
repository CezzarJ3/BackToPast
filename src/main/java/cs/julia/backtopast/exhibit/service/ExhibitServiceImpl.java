package cs.julia.backtopast.exhibit.service;

import cs.julia.backtopast.department.dao.DepartmentRepository;
import cs.julia.backtopast.department.domain.Department;
import cs.julia.backtopast.exhibit.controller.dto.ExhibitDto;
import cs.julia.backtopast.exhibit.dao.ExhibitRepository;
import cs.julia.backtopast.exhibit.domain.Exhibit;
import cs.julia.backtopast.storage.dao.StorageRepository;
import cs.julia.backtopast.storage.domain.Storage;
import cs.julia.backtopast.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class ExhibitServiceImpl implements ExhibitService {
    private final ExhibitRepository exhibitRepository;
    private final DepartmentRepository departmentRepository;
    private final StorageRepository storageRepository;

    @Autowired
    public ExhibitServiceImpl(ExhibitRepository exhibitRepository, DepartmentRepository departmentRepository, StorageRepository storageRepository) {
        this.exhibitRepository = exhibitRepository;
        this.departmentRepository = departmentRepository;
        this.storageRepository = storageRepository;
    }

    @Override
    @Transactional
    public void createExhibit(ExhibitDto exhibitDto) {
        Department department = departmentRepository.findById(exhibitDto.type())
                .orElseThrow(() -> new EntityNotFoundException("Отдел #[%s] не найден".formatted(exhibitDto.type())));
        Storage storage = storageRepository.findById(exhibitDto.store())
                .orElseThrow(() -> new EntityNotFoundException("Отдел #[%s] не найден".formatted(exhibitDto.store())));

        Exhibit exhibit = new Exhibit()
                .setName(exhibitDto.name())
                .setDescription(exhibitDto.description())
                .setYear(exhibitDto.year())
                .setAuthor(exhibitDto.author())
                .setDepartment(department)
                .setStorage(storage);
        exhibitRepository.save(exhibit);
    }

    @Override
    @Transactional
    public Collection<Exhibit> findExhibitsByName(String name) {
        return (Collection<Exhibit>) exhibitRepository.findExhibitByNameContainingIgnoreCase(name);
    }

    @Override
    public Collection<Exhibit> findExhibitsByYear(int year) {
        return (Collection<Exhibit>) exhibitRepository.findExhibitByYear(year);
    }

    @Override
    public Collection<Exhibit> findExhibitsByNameAndYear(String name, int year) {
        return (Collection<Exhibit>) exhibitRepository.findExhibitByNameContainingIgnoreCaseAndYear(name, year);
    }

    @Override
    public void deleteExhibit(int exhibitId) {
        exhibitRepository.deleteById(exhibitId);
    }

    @Override
    @Transactional
    public void updateExhibitDescription(Integer exhibitId, String newDescription) {
        exhibitRepository
                .findById(exhibitId)
                .ifPresentOrElse(exhibit -> {
                    exhibit.setDescription(newDescription);
                }, () -> {
                    throw new EntityNotFoundException("Экспонат #[%s] не найден".formatted(exhibitId));
                });
    }

    @Override
    public Page<Exhibit> findPaginated(Pageable pageable, String name, int year) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = pageSize * currentPage;
//        List<Exhibit> exhibitsByName = (List<Exhibit>) exhibitRepository.findExhibitByNameContainingIgnoreCase(name);
//        List<Exhibit> exhibitsByYear = (List<Exhibit>) exhibitRepository.findExhibitByYear(year);

        List<Exhibit> exhibits;
        if (year > -1) {
            exhibits = (List<Exhibit>) exhibitRepository.findExhibitByNameContainingIgnoreCaseAndYear(name, year);
        } else {
            exhibits = (List<Exhibit>) exhibitRepository.findExhibitByNameContainingIgnoreCase(name);
        }

        List<Exhibit> list;
        if (exhibits.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, exhibits.size());
            list = exhibits.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), exhibits.size());
    }

    @Override
    public Exhibit findById(int id) {
        return exhibitRepository.findById(id).get();
    }
}
