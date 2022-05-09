package com.example.user.credentials;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialUserRepository extends JpaRepository<CredentialUserEntity, Long> {
}
