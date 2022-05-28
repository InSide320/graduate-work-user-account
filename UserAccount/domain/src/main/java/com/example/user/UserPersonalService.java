package com.example.user;

import com.example.exception.NotFoundException;
import com.example.exception.ValidationException;
import com.example.user.credentials.CredentialUserRepository;
import com.example.user.details.DetailUserRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public record UserPersonalService(
        UserPersonalDataRepository userPersonalDataRepository,
        DetailUserRepository detailUserRepository,
        CredentialUserRepository credentialUserRepository) implements UserDetailsService {

    public void saveAndFlush(UserPersonalDataEntity userPersonalDataEntity) {
        checkValidationForSave(userPersonalDataEntity);
        userPersonalDataRepository.saveAndFlush(userPersonalDataEntity);
    }

    public UserPersonalDataEntity save(UserPersonalDataEntity userPersonalDataEntity)
            throws ValidationException {
        checkValidationForSave(userPersonalDataEntity);
        return userPersonalDataRepository.save(userPersonalDataEntity);
    }

    public List<UserPersonalDataEntity> getInformationByGroup(String group) {
        if (group.isBlank()) {
            throw new ValidationException("Group is blank. Set data");
        }
        return userPersonalDataRepository
                .getUserPersonalDataEntitiesByDetailUserEntityEqualsGroup(group);
    }

    public UserPersonalDataEntity findOne(Example<UserPersonalDataEntity> example)
            throws NotFoundException {
        return userPersonalDataRepository.findOne(example).orElseThrow(() ->
                new NotFoundException("Such personal user data does not exist"));
    }

    public UserPersonalDataEntity findByLastNameTransliteration(String lastNameTransliteration)
            throws NotFoundException {
        return userPersonalDataRepository.findByLastNameTransliteration(lastNameTransliteration)
                .orElseThrow(() -> new NotFoundException("This surname does not exist"));
    }

    public List<UserPersonalDataEntity> findAll() {
        return userPersonalDataRepository.findAll();
    }

    public UserPersonalDataEntity getUserById(long id) throws NotFoundException {
        checkForId(id);
        return userPersonalDataRepository.getById(id);
    }

    public boolean deleteById(Long id) throws NotFoundException {
        checkForId(id);
        userPersonalDataRepository.deleteById(id);
        return true;
    }

    private void checkForId(Long id) throws NotFoundException {
        if (!Optional.of(userPersonalDataRepository.findAll().size() >= id).orElse(id > 0)) {
            throw new NotFoundException("ID cannot be larger or less");
        }
    }

    public List<UserPersonalDataEntity> findAllWithSorting() throws NotFoundException {
        if (userPersonalDataRepository.count() == 0L) {
            throw new NotFoundException("Table is empty");
        } else if (userPersonalDataRepository.findAll().isEmpty()) {
            throw new NotFoundException("UserPersonalData is empty");
        }

        return userPersonalDataRepository.findAll(Sort.by(Sort.Direction.ASC));
    }

    private void checkValidationForSave(UserPersonalDataEntity userPersonalDataEntity) {
        if (userPersonalDataEntity == null) {
            throw new ValidationException("user personal data is empty");
        }
        if (userPersonalDataEntity.getFirstNameTransliteration().isBlank()
                || userPersonalDataEntity.getFirstNameTransliteration().length() < 2) {
            throw new ValidationException("First name transliteration isBlank");
        }
        if (userPersonalDataEntity.getLastNameTransliteration().isBlank()
                || userPersonalDataEntity.getLastNameTransliteration().length() < 2) {
            throw new ValidationException("Last name transliteration isBlank");
        }
        if (userPersonalDataEntity.getLastNameCyrillic().isBlank()
                || userPersonalDataEntity.getLastNameCyrillic().length() < 2) {
            throw new ValidationException("Last name cyrillic isBlank");
        }
        if (userPersonalDataEntity.getFirstNameCyrillic().isBlank()
                || userPersonalDataEntity.getFirstNameCyrillic().length() < 2) {
            throw new ValidationException("First name cyrillic isBlank");
        }
        if (userPersonalDataEntity.getMidlNameCyrillic().isBlank()
                || userPersonalDataEntity.getMidlNameCyrillic().length() < 2) {
            throw new ValidationException("Midl name cyrillic isBlank");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return credentialUserRepository.loadUserByUsername(username)
                .orElseThrow(() -> new NotFoundException("Email not registered: " + username));
    }

}
