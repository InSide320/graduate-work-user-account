package com.example.user.credentials;

import com.example.user.credentials.generate.GenerateCredentialsEmail;
import com.example.user.credentials.generate.GenerateCredentialsPassword;
import com.example.user.credentials.role.RoleType;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CredentialUserRepository.class)
@DataJpaTest
@EnableAutoConfiguration
class CredentialsUserRepositoryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CredentialUserRepository credentialUserRepository;
    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("TRUNCATE TABLE credential_users");
    }

    @AfterEach
    void tearDown() {
        JdbcTestUtils.dropTables(
                namedJdbcTemplate.getJdbcTemplate(),
                "credential_users"
        );
    }

    @Test
    @DisplayName("credential user was deleted")
    void credentialUser_was_deleted() {
        CredentialUserEntity credentialUserEntity
                = new CredentialUserEntity(GenerateCredentialsEmail
                .generateEmail("Kud", "Denis"),
                GenerateCredentialsPassword.generateStrongPassword(),
                RoleType.STUDENT, null);
        credentialUserRepository.save(credentialUserEntity);

        AssertionsForClassTypes.assertThat(credentialUserRepository.count()).isEqualTo(1);
        credentialUserRepository.delete(credentialUserEntity);
        AssertionsForClassTypes.assertThat(credentialUserRepository.count()).isZero();
    }

    @Test
    @DisplayName("Save multiply credential users")
    @DirtiesContext
    void saveTheEntity_whenInitializingTheObject_thenCheckTheCount() {

        credentialUserRepository.save(new CredentialUserEntity(GenerateCredentialsEmail
                .generateEmail("Kud", "Denis"),
                GenerateCredentialsPassword.generateStrongPassword(), RoleType.STUDENT, null));

        credentialUserRepository.save(new CredentialUserEntity(GenerateCredentialsEmail
                .generateEmail("Kud", "Denis"),
                GenerateCredentialsPassword.generateStrongPassword(), RoleType.STUDENT, null));
        System.out.println(credentialUserRepository.findAll());

        AssertionsForClassTypes.assertThat(credentialUserRepository.count()).isEqualTo(2);
    }

    @Test
    @DisplayName("Find all credential")
    @DirtiesContext
    void queryFindAll_whenSaveEntity_thenEqualsNewEntity() {
        credentialUserRepository.save(new CredentialUserEntity(
                "sd",
                "sd",
                RoleType.STUDENT, null)
        );
        List<CredentialUserEntity> list = credentialUserRepository.findAll();

        list.add(new CredentialUserEntity(
                "sd",
                "sd",
                RoleType.STUDENT, null)
        );
        AssertionsForClassTypes.assertThat(list.get(0).toString())
                .hasToString(list.get(1).toString());
    }

    @Test
    @DirtiesContext
    @DisplayName("Update role type user")
    void queryUpdateRoleTypeUser_whenInputIdAndNewRoleType_thenEqualUpdateOne() {
        credentialUserRepository.save(new CredentialUserEntity(
                "ssd",
                "sds",
                RoleType.STUDENT,
                "sds"));
        AssertionsForClassTypes.assertThat(1)
                .isEqualTo(credentialUserRepository.updateRoleTypeUser(1, RoleType.TEACHER));
    }
}