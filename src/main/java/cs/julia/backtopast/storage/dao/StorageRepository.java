package cs.julia.backtopast.storage.dao;

import cs.julia.backtopast.storage.domain.Storage;
import org.springframework.data.repository.CrudRepository;

public interface StorageRepository extends CrudRepository<Storage, Integer> {
    Iterable<Storage> findStoragesByDepartmentId(int departmentId);
}
