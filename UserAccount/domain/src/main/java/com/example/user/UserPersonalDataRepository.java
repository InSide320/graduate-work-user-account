package com.example.user;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserPersonalDataRepository extends JpaRepository<UserPersonalData, Long> {

    <S extends UserPersonalData> S save(S entity);

    <S extends UserPersonalData> List<S> findAll(Example<S> example);

    List<UserPersonalData> findAll();

    Optional<UserPersonalData> findById(Long aLong);

    @Query("select upd from UserPersonalData upd where upd.id=:id")
    UserPersonalData getById(@Param("id") Long aLong);

    void delete(UserPersonalData entity);
}
