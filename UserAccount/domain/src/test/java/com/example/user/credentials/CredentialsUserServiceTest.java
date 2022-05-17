package com.example.user.credentials;

import com.example.user.credentials.generate.GenerateCredentialsEmail;
import com.example.user.credentials.generate.GenerateCredentialsPassword;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CredentialsUserService.class)
@DataJpaTest
@EnableAutoConfiguration
class CredentialsUserServiceTest {
    @Autowired
    CredentialsUserService credentialsUserService;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("TRUNCATE TABLE credential_users");
    }

    @Test
    @DisplayName("User credentials were found")
    void userCredentials_wereFound() {
        credentialsUserService.save(
                new CredentialUserEntity("email", "password"));
        AssertionsForClassTypes.assertThat(credentialsUserService.findAll().toString())
                .hasToString(
                        List.of(
                                new CredentialUserEntity(
                                        "email",
                                        "password"
                                )
                        ).toString()
                );

    }

    @Test
    @DisplayName("Credential user was saved")
    void credentialUser_was_saved() {
        credentialsUserService.save(new CredentialUserEntity(
                GenerateCredentialsEmail.generateEmail(
                        "Kud", "Denis"),
                GenerateCredentialsPassword.generateStrongPassword())
        );
        List<CredentialUserEntity> list = credentialsUserService.findAll();
        AssertionsForClassTypes.assertThat(list.size())
                .isEqualTo(1);
        AssertionsForClassTypes
                .assertThat(list.get(0).getClass())
                .isEqualTo(CredentialUserEntity.class);
    }

    @Test
    @DisplayName("Object has to string and equals new entity to string")
    void getCredentialUsers_whenSetDataEntity_thenHasToStringAndEqualsNewEntityToString() {
        credentialsUserService
                .save(new CredentialUserEntity("sd", "sd"));
        AssertionsForClassTypes
                .assertThat(credentialsUserService.findAll().get(0).toString())
                .hasToString(new CredentialUserEntity("sd", "sd").toString()
                );
    }
}