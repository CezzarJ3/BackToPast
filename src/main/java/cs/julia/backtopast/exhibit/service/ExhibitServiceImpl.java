package cs.julia.backtopast.exhibit.service;

import cs.julia.backtopast.department.dao.DepartmentRepository;
import cs.julia.backtopast.department.domain.Department;
import cs.julia.backtopast.exhibit.controller.dto.ExhibitDto;
import cs.julia.backtopast.exhibit.dao.ExhibitRepository;
import cs.julia.backtopast.exhibit.domain.Exhibit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class ExhibitServiceImpl implements ExhibitService {
    private final ExhibitRepository exhibitRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public ExhibitServiceImpl(ExhibitRepository exhibitRepository, DepartmentRepository departmentRepository) {
        this.exhibitRepository = exhibitRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Transactional
    public void createExhibit(ExhibitDto exhibitDto) {
        Department department = departmentRepository.findById(exhibitDto.type())
                .orElseThrow(() -> new EntityNotFoundException("Отдел #[%s] не найден".formatted(exhibitDto.type())));

        Exhibit exhibit = new Exhibit()
                .setName(exhibitDto.name())
                .setDescription(exhibitDto.description())
                .setYear(exhibitDto.year())
                .setAuthor(exhibitDto.author())
                .setDepartment(department);
        exhibitRepository.save(exhibit);
    }

    @Override
    @Transactional
    public Collection<Exhibit> findExhibitsByName(String name) {
        return (Collection<Exhibit>) exhibitRepository.findExhibitByNameContainingIgnoreCase(name);
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
                    throw new EntityNotFoundException("Продукт #[%s] не найден".formatted(exhibitId));
                });
    }

    @Override
    public Page<Exhibit> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = pageSize * currentPage;
        List<Exhibit> exhibits = (List<Exhibit>) exhibitRepository.findAll();

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
