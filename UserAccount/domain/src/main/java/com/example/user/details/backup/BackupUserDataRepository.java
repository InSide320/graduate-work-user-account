package com.example.user.details.backup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BackupUserDataRepository extends JpaRepository<BackupUserDataEntity, Long> {
}
