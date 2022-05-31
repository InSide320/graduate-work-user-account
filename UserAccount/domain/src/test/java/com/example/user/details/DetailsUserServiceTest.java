package com.example.user.details;

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
@ContextConfiguration(classes = {DetailsUserService.class})
@DataJpaTest
@EnableAutoConfiguration
class DetailsUserServiceTest {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    DetailsUserService detailsUserService;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("TRUNCATE TABLE detail_users");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() {
    }

    @Test
    void save() {
    }

    @Test
    void saveAndFlush() {
    }

    @Test
    void updatesDatesIntroducedAndReleased() {
    }
}