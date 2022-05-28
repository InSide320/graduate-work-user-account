package com.example.user.credentials;

import com.example.exception.NotFoundException;
import com.example.exception.ValidationException;
import com.example.sender.MailSenderService;
import com.example.user.credentials.role.RoleType;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public record CredentialsUserService(
        CredentialUserRepository credentialUserRepository,
        MailSenderService mailSenderService,
        PasswordEncoder encoder) {

    public List<CredentialUserEntity> findAll() {
        return credentialUserRepository.findAll();
    }

    public CredentialUserEntity save(CredentialUserEntity entity) {
        entity.setActivationCode(UUID.randomUUID().toString());
        return credentialUserRepository.save(entity);
    }

    public CredentialUserEntity saveAndFlush(CredentialUserEntity entity) {
        if (entity.getRoleType() == null) {
            throw new ValidationException("Role type is empty");
        }
        entity.setActivationCode(UUID.randomUUID().toString());
        if (!entity.getBackupEmail().isEmpty()) {
            String message = String.format("Hello, %s! \n"
                            + "Welcome to UserAccount. Please visit next link: "
                            + "http://localhost:8080/activate/%s"
                            + " \nYour login email: %s \nPassword: %s",
                    entity.getBackupEmail(),
                    entity.getActivationCode(),
                    entity.getAuthEmail(),
                    entity.getPassword()
            );
            entity.setAuthPassword(encoder.encode(entity.getAuthPassword()));
            mailSenderService.send(entity.getBackupEmail(), "Activation code and credential data", message);
        }
        return credentialUserRepository.saveAndFlush(entity);
    }

    public Integer updateRoleTypeUser(long id, RoleType roleType) {
        if (roleType == null) {
            throw new ValidationException("Role type is null");
        }
        return credentialUserRepository.updateRoleTypeUser(id, roleType);
    }

    public boolean changePasswordUser(
            CredentialUserEntity entity,
            String oldPassword,
            String newPassword,
            String repeatedPassword) {
        if (encoder.matches(oldPassword, entity.getAuthPassword())) {
            if (newPassword.equals(repeatedPassword)) {
                String message = String.format("Hello, %s! \n"
                                + "You have changed old password to: %s \n Don't show this message to anyone!",
                        entity.getAuthEmail(),
                        newPassword);
                mailSenderService.send(entity.getBackupEmail(), "Changed password", message);
            } else {
                throw new IllegalArgumentException("Password doesn't match");
            }
        } else {
            throw new IllegalArgumentException("Invalid password");
        }
        entity.setAuthPassword(encoder.encode(newPassword));
        credentialUserRepository.saveAndFlush(
                entity
        );

        return true;
    }

    public boolean activateUser(String code) {
        CredentialUserEntity credentialUserEntity = credentialUserRepository.findByActivationCode(code);
        if (credentialUserEntity == null) {
            return false;
        }
        credentialUserEntity.setActivationCode(null);
        credentialUserRepository.saveAndFlush(credentialUserEntity);
        return true;
    }

    public List<CredentialUserEntity> findAllWithSortingAsc() {
        if (credentialUserRepository.findAll().isEmpty()) {
            throw new NotFoundException("Received data is empty");
        }
        return credentialUserRepository.findAll(Sort.by(Sort.Direction.ASC, "roleType"));
    }

    public List<CredentialUserEntity> findAllWithSortingRoleTypeByDesc() {
        return credentialUserRepository.findAll(Sort.by(Sort.Direction.DESC, "roleType"));
    }
}
