package com.example.user.credentials;

import com.example.user.credentials.role.RoleType;
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

    public CredentialUserEntity saveAndFlush(CredentialUserEntity entity) {
        return credentialUserRepository.saveAndFlush(entity);
    }

    public Integer updateRoleTypeUser(long id, RoleType roleType) {
        return credentialUserRepository.updateRoleTypeUser(id, roleType);
    }

}
