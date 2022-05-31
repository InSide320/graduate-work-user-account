package com.example.user.details.backup;

import com.example.exception.NotFoundException;
import com.example.exception.ValidationException;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {BackupUserDataService.class})
@DataJpaTest
@EnableAutoConfiguration
class BackupUserDataServiceTest {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    BackupUserDataService backupUserDataService;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("TRUNCATE TABLE backup_users_data");
    }

    @Test
    void findAllBackupUserData_whenAddInTableData_thenListIsEqualToAllDataTable() {
        List<BackupUserDataEntity> backupUserDataEntityList
                = new ArrayList<>(
                List.of(
                        new BackupUserDataEntity("+123"),
                        new BackupUserDataEntity("+321")
                )
        );
        backupUserDataService.save(backupUserDataEntityList.get(0));
        backupUserDataService.save(backupUserDataEntityList.get(1));
        AssertionsForClassTypes.assertThat(backupUserDataEntityList)
                .isEqualTo(backupUserDataService.findAll());
    }

    @Test
    @DisplayName("Save backup user data")
    void saveBackupUserData_whenInputServiceSave_thenEntityIsEqualToReturnedMethodSave() {
        BackupUserDataEntity backupUserDataEntity = new BackupUserDataEntity("+380");
        AssertionsForClassTypes.assertThat(backupUserDataEntity)
                .isEqualTo(backupUserDataService.save(backupUserDataEntity));
    }

    @Test
    @DisplayName("Save and flush backup user data")
    void saveAndFlushBackupUserData_whenInputServiceSaveAndFlush_thenEntityIsEqualToReturneMethodSaveAndFlush() {
        BackupUserDataEntity backupUserDataEntity = new BackupUserDataEntity("+380");
        AssertionsForClassTypes.assertThat(backupUserDataEntity)
                .isEqualTo(backupUserDataService.saveAndFlush(backupUserDataEntity));
    }

    @Test
    @DisplayName("Is null phone number save")
    void checkIsNullPhoneNumberSave() {
        BackupUserDataEntity backupUserDataEntity = new BackupUserDataEntity();
        assertThrows(NotFoundException.class,
                () -> backupUserDataService.save(backupUserDataEntity)
        );
    }

    @Test
    @DisplayName("Is null phone number save and flush")
    void checkIsNullPhoneNumberSaveAndFlush() {
        BackupUserDataEntity backupUserDataEntity = new BackupUserDataEntity();
        assertThrows(NotFoundException.class,
                () -> backupUserDataService.saveAndFlush(backupUserDataEntity)
        );
    }

    @Test
    @DisplayName("Is null backupUserData save and flush")
    void checkIsNullEntitySaveAndFlush() {
        assertThrows(NullPointerException.class,
                () -> backupUserDataService.saveAndFlush(null)
        );
    }

    @Test
    @DisplayName("Is null backupUserData save")
    void checkIsNullEntitySave() {
        assertThrows(NullPointerException.class,
                () -> backupUserDataService.saveAndFlush(null)
        );
    }

    @Test
    @DisplayName("Is blank phone number save and flush")
    void checkIsBlankPhoneNumberSaveAndFlush() {
        BackupUserDataEntity backupUserDataEntity = new BackupUserDataEntity("");
        assertThrows(ValidationException.class,
                () -> backupUserDataService.saveAndFlush(backupUserDataEntity)
        );
    }

    @Test
    @DisplayName("Is blank phone number save and flush")
    void checkIsBlankPhoneNumberSave() {
        BackupUserDataEntity backupUserDataEntity = new BackupUserDataEntity("");
        assertThrows(ValidationException.class,
                () -> backupUserDataService.saveAndFlush(backupUserDataEntity)
        );
    }

}