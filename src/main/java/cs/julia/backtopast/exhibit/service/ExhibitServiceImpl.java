package cs.julia.backtopast.exhibit.service;

import cs.julia.backtopast.exhibit.dao.ExhibitRepository;
import cs.julia.backtopast.exhibit.domain.Exhibit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ExhibitServiceImpl implements ExhibitService {
    private ExhibitRepository exhibitRepository;

    @Autowired
    public ExhibitServiceImpl(ExhibitRepository exhibitRepository) {
        this.exhibitRepository = exhibitRepository;
    }

    @Override
    public void create(Exhibit exhibit) {
        exhibitRepository.save(exhibit);
    }

    @Override
    public Collection<Exhibit> findExhibitsByName(String name) {
        return (Collection<Exhibit>) exhibitRepository.findExhibitByNameContainingIgnoreCase(name);
    }

    @Override
    public Collection<Exhibit> findAllExhibits() {
        return (Collection<Exhibit>) exhibitRepository.findAll();
    }
}
