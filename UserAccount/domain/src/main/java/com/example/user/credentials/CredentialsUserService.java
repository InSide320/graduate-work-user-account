package com.example.user.credentials;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record CredentialsUserService(CredentialUserRepository credentialUserRepository) {

    public List<CredentialUserEntity> findAll() {
        return credentialUserRepository.findAll();
    }

    public CredentialUserEntity save(CredentialUserEntity entity) {
        return credentialUserRepository.save(entity);
    }
}
