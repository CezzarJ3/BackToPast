package cs.julia.backtopast.storage.service;

import cs.julia.backtopast.department.dao.DepartmentRepository;
import cs.julia.backtopast.department.domain.Department;
import cs.julia.backtopast.storage.controller.dto.StorageDto;
import cs.julia.backtopast.storage.dao.StorageRepository;
import cs.julia.backtopast.storage.domain.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Service
public class StorageServiceImpl implements StorageService {
    private final StorageRepository storageRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public StorageServiceImpl(StorageRepository storageRepository, DepartmentRepository departmentRepository) {
        this.storageRepository = storageRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void createStorage(StorageDto storageDto) {
        Department department = departmentRepository.findById(storageDto.type())
                .orElseThrow(() -> new EntityNotFoundException("Отдел #[%s] не найден".formatted(storageDto.type())));

        Storage storage = new Storage()
                .setDepartment(department)
                .setManager(storageDto.manager());
        storageRepository.save(storage);
    }

    @Override
    public Collection<Storage> findStoragesByDepartment(int departmentId) {
        return (Collection<Storage>) storageRepository.findStoragesByDepartmentId(departmentId);
    }

    @Override
    public void deleteStorage(int storageId) {
        storageRepository.deleteById(storageId);
    }

    @Override
    public Storage findById(int id) {
        return storageRepository.findById(id).get();
    }

    @Override
    public Collection<Storage> findAll() {
        return (Collection<Storage>) storageRepository.findAll();
    }
}
