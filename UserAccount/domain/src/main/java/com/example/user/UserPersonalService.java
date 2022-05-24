package com.example.user;

import com.example.exception.NotFoundException;
import com.example.user.credentials.CredentialUserEntity;
import com.example.user.credentials.CredentialUserRepository;
import com.example.user.details.DetailUserRepository;
import org.springframework.data.domain.Example;
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
        userPersonalDataRepository.saveAndFlush(userPersonalDataEntity);
    }

    public List<UserPersonalDataEntity> getInformationByGroup(String group) {
        return userPersonalDataRepository
                .getUserPersonalDataEntitiesByDetailUserEntityEqualsGroup(group);
    }

    public UserPersonalDataEntity save(UserPersonalDataEntity userPersonalDataEntity) {
        return userPersonalDataRepository.save(userPersonalDataEntity);
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return credentialUserRepository.loadUserByUsername(username)
                .orElseThrow(() -> new NotFoundException("Email not registered: " + username));
    }
}
