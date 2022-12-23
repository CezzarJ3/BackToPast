package cs.julia.backtopast.storage.service;

import cs.julia.backtopast.storage.controller.dto.StorageDto;
import cs.julia.backtopast.storage.domain.Storage;

import java.util.Collection;

public interface StorageService {
    void createStorage(StorageDto storageDto);

    Collection<Storage> findStoragesByDepartment(int departmentId);

    void deleteStorage(int storageId);

    Storage findById(int id);
}
