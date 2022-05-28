package com.example.user.details.backup;

import com.example.exception.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record BackupUserDataService(
        BackupUserDataRepository backupUserDataRepository) {

    public List<BackupUserDataEntity> findAll() {
        return backupUserDataRepository.findAll();
    }

    public BackupUserDataEntity save(BackupUserDataEntity backupUserDataEntity) {
        if (backupUserDataEntity.getPhoneNumber().isBlank()) {
            throw new ValidationException("Phone number isBlank");
        }
        return backupUserDataRepository.save(backupUserDataEntity);
    }

    public BackupUserDataEntity saveAndFlush(BackupUserDataEntity backupUserDataEntity) {
        if (backupUserDataEntity.getPhoneNumber().isBlank()) {
            throw new ValidationException("Phone number isBlank");
        }
        return backupUserDataRepository.saveAndFlush(backupUserDataEntity);
    }
}
