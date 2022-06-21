package com.example.user.details.introductory;

import com.example.exception.NotFoundException;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {EstimatesService.class})
@DataJpaTest
@EnableAutoConfiguration
class EstimatesServiceTest {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    EstimatesService estimatesService;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("TRUNCATE TABLE student_entrance_scores");
    }

    @Test
    void save() {
        EstimatesEntity estimatesEntity = new EstimatesEntity();
        AssertionsForClassTypes.assertThat(estimatesEntity)
                .isEqualTo(estimatesService.save(estimatesEntity));
    }

    @Test
    @DirtiesContext
    void updateEstimates() {
        EstimatesEntity estimatesEntity = new EstimatesEntity(1, 2, 3);
        estimatesService.save(estimatesEntity);

        AssertionsForClassTypes.assertThat(0)
                .isEqualTo(estimatesService.updateEstimates(1, 1, 1, 1));
    }

    @Test
    void saveAndFlush() {
        EstimatesEntity estimatesEntity = new EstimatesEntity();
        AssertionsForClassTypes.assertThat(estimatesEntity)
                .isEqualTo(estimatesService.saveAndFlush(estimatesEntity));
    }

    @Test
    void checkNullPointerException() {
        assertThrows(
                NullPointerException.class,
                () -> estimatesService.save(null));
    }

    @Test
    void checkIsNullUkLangVariable() {
        assertThrows(
                NullPointerException.class,
                () -> estimatesService
                        .updateEstimates(
                                null,
                                null,
                                null,
                                0)
        );
    }

    @Test
    void checkIsNullMathSubjectVariable() {
        assertThrows(
                NullPointerException.class,
                () -> estimatesService
                        .updateEstimates(
                                1,
                                null,
                                null,
                                0)
        );
    }

    @Test
    void checkIsNullAdditionalSubjectVariable() {
        assertThrows(
                NullPointerException.class,
                () -> estimatesService
                        .updateEstimates(
                                1,
                                1,
                                null,
                                0)
        );
    }

    @Test
    void checkIsNullIdVariable() {
        assertThrows(
                NotFoundException.class,
                () -> estimatesService
                        .updateEstimates(
                                1,
                                1,
                                1,
                                2)
        );
    }

    @Test
    void checkIllegalArgumentUkLanguageSubjectMustBeInTheRange() {
        assertThrows(
                IllegalArgumentException.class,
                () -> estimatesService
                        .updateEstimates(
                                0,
                                1,
                                1,
                                1)
        );
    }

    @Test
    void checkIllegalArgumentMathSubjectMustBeInTheRange() {
        assertThrows(
                IllegalArgumentException.class,
                () -> estimatesService
                        .updateEstimates(
                                1,
                                0,
                                1,
                                1)
        );
    }

    @Test
    void checkIllegalArgumentAdditionalSubjectMustBeInTheRange() {
        assertThrows(
                IllegalArgumentException.class,
                () -> estimatesService
                        .updateEstimates(
                                1,
                                1,
                                0,
                                1)
        );
    }

}