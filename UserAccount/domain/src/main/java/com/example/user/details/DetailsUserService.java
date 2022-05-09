package com.example.user.details;

import com.example.user.details.backup.BackupUserDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record DetailsUserService(
        DetailUserRepository detailUserRepository,
        BackupUserDataRepository backupUserDataRepository) {


    public List<DetailUserEntity> findAll() {
        return detailUserRepository.findAll();
    }

    public DetailUserEntity save(DetailUserEntity entity) {
        return detailUserRepository.save(entity);
    }
}
