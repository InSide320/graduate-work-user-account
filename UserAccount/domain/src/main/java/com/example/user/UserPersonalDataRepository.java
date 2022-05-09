package com.example.user;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserPersonalDataRepository extends JpaRepository<UserPersonalDataEntity, Long> {

    @Query("select upd.lastNameTransliteration, upd.firstNameTransliteration "
            + "from UserPersonalDataEntity upd "
            + "where upd.lastNameTransliteration=:lastNameTransliteration")
    <S extends UserPersonalDataEntity> Optional<S> findByLastNameTransliteration(
            @Param("lastNameTransliteration") String lastNameTransliteration);

    @NonNull
    @Override
    <S extends UserPersonalDataEntity> List<S> findAll(@NonNull Example<S> example);

    @NonNull
    @Override
    List<UserPersonalDataEntity> findAll();

    @NonNull
    @Override
    <S extends UserPersonalDataEntity> Optional<S> findOne(@NonNull Example<S> example);

    @NonNull
    Optional<UserPersonalDataEntity> findById(@NonNull Long id);

    @NonNull
    UserPersonalDataEntity getById(@NonNull Long id);

    @Override
    void delete(@NonNull UserPersonalDataEntity entity);

    void deleteById(@NonNull Long id);

}
