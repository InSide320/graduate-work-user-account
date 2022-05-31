package com.example.user.credentials;

import com.example.config.JavaMailSenderConfiguration;
import com.example.exception.ValidationException;
import com.example.sender.MailSenderService;
import com.example.user.credentials.generate.GenerateCredentialsEmail;
import com.example.user.credentials.generate.GenerateCredentialsPassword;
import com.example.user.credentials.role.RoleType;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        CredentialsUserService.class, MailSenderService.class,
        JavaMailSenderConfiguration.class, PasswordEncoder.class,
        BCryptPasswordEncoder.class})
@DataJpaTest
@EnableAutoConfiguration
class CredentialsUserServiceTest {
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    CredentialsUserService credentialsUserService;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private MailSenderService mailSenderService;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("TRUNCATE TABLE credential_users");
    }

    @Test
    @DisplayName("User credentials were found")
    void userCredentials_wereFound() {
        encoder.encode("sds");
        credentialsUserService.save(
                new CredentialUserEntity(
                        "email",
                        "password",
                        RoleType.STUDENT,
                        null)
        );
        AssertionsForClassTypes.assertThat(credentialsUserService.findAll().toString())
                .hasToString(
                        List.of(
                                new CredentialUserEntity(
                                        "email",
                                        "password",
                                        RoleType.STUDENT, null
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
                GenerateCredentialsPassword.generateStrongPassword(), RoleType.STUDENT, null)
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
                .save(new CredentialUserEntity("sd", "sd",
                        RoleType.STUDENT, null));
        AssertionsForClassTypes
                .assertThat(credentialsUserService.findAll().get(0).toString())
                .hasToString(new CredentialUserEntity("sd", "sd",
                        RoleType.STUDENT, null).toString()
                );
    }

    @Test
    @DirtiesContext
    @DisplayName("Save and flush")
    void saveAndResetUserCredentials_whenWillTheRegistrationInformationArrive_thenEqualToUserCredentials() {
        CredentialUserEntity credentialUserEntity = new CredentialUserEntity(
                "sds", "sds",
                RoleType.STUDENT, "bekasi4824@sinyago.com"
        );
        AssertionsForClassTypes.assertThat(credentialUserEntity)
                .isEqualTo(credentialsUserService.saveAndFlush(credentialUserEntity));
    }

    @Test
    @DisplayName("Exception handler role type is null")
    void checkExceptionRoleTypeIsNull() {
        CredentialUserEntity credentialUserEntity
                = new CredentialUserEntity(
                "ss", "s", null, "wew@s"
        );
        Assertions.assertThrows(ValidationException.class,
                () -> credentialsUserService.saveAndFlush(credentialUserEntity));
    }

    @Test
    @DisplayName("Exception handler backup email is empty")
    void checkExceptionBackupEmailIsEmpty() {
        CredentialUserEntity credentialUserEntity
                = new CredentialUserEntity(
                "ss",
                "s", RoleType.STUDENT,
                ""
        );
        Assertions.assertThrows(ValidationException.class,
                () -> credentialsUserService.saveAndFlush(credentialUserEntity));
    }
}