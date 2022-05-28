package com.example.user.credentials;

import com.example.user.credentials.role.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CredentialUserRepository extends JpaRepository<CredentialUserEntity, Long> {

    @Query("select credential "
            + "from CredentialUserEntity as credential "
            + "where credential.authEmail=:authEmail")
    Optional<CredentialUserEntity> loadUserByUsername(@Param("authEmail") String email);

    @Modifying
    @Transactional
    @Query("update CredentialUserEntity "
            + "set roleType=:roleType "
            + "where id=:id")
    Integer updateRoleTypeUser(@Param("id") long id, @Param("roleType") RoleType roleType);

    CredentialUserEntity findByActivationCode(String code);
}
