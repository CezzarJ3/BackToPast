package cs.julia.backtopast.exhibit.service;

import cs.julia.backtopast.exhibit.domain.Exhibit;

import java.util.Collection;

public interface ExhibitService {
    void create(Exhibit exhibit);

    Collection<Exhibit> findExhibitsByName(String name);

    Collection<Exhibit> findAllExhibits();
}
