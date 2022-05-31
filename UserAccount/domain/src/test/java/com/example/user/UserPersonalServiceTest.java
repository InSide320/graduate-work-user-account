package com.example.user;

import com.example.user.details.introductory.EstimatesService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {UserPersonalService.class})
@DataJpaTest
@EnableAutoConfiguration
class UserPersonalServiceTest {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserPersonalService userPersonalService;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("TRUNCATE TABLE users_personal_data");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveAndFlush() {
    }

    @Test
    void save() {
    }

    @Test
    void getInformationByGroup() {
    }

    @Test
    void findOne() {
    }

    @Test
    void findByLastNameTransliteration() {
    }

    @Test
    void findAll() {
    }

    @Test
    void getUserById() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void findAllWithSorting() {
    }
}