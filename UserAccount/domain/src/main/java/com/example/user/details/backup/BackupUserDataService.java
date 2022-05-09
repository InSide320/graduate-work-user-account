package com.example.user.details.backup;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record BackupUserDataService(
        BackupUserDataRepository backupUserDataRepository) {

    public List<BackupUserDataEntity> findAll() {
        return backupUserDataRepository.findAll();
    }

    public BackupUserDataEntity save(BackupUserDataEntity backupUserDataEntity) {
        return backupUserDataRepository.save(backupUserDataEntity);
    }
}
