package com.example.user.details;

import com.example.exception.NotFoundException;
import com.example.exception.ValidationException;
import com.example.user.details.backup.BackupUserDataRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public record DetailsUserService(
        DetailUserRepository detailUserRepository,
        BackupUserDataRepository backupUserDataRepository) {


    public List<DetailUserEntity> findAll() {
        return detailUserRepository.findAll();
    }

    public DetailUserEntity save(DetailUserEntity entity) {

        checkValidationException(entity);

        return detailUserRepository.save(entity);
    }

    public DetailUserEntity saveAndFlush(DetailUserEntity entity) {

        checkValidationException(entity);

        return detailUserRepository.saveAndFlush(entity);
    }

    public DetailUserEntity updatesDatesIntroducedAndReleased(
            DetailUserEntity detailUser,
            LocalDate enter,
            LocalDate release
            ) {
        if (detailUserRepository.findById(detailUser.getId()).isEmpty()) {
            throw new NotFoundException("Details user not found");
        }
        DetailUserEntity detailUserEntity = detailUserRepository.getById(detailUser.getId());
        detailUserEntity.setDateEnter(enter);
        detailUserEntity.setDateRelease(release);
        return detailUserRepository.saveAndFlush(detailUserEntity);
    }

    private void checkValidationException(DetailUserEntity entity) {
        if (entity.getGroupTransliteration().isBlank()) {
            throw new ValidationException("Group transliteration isBlank");
        }

        if (entity.getGroupCyrillic().isBlank()) {
            throw new ValidationException("Group cyrillic isBlank");
        }
    }
}
