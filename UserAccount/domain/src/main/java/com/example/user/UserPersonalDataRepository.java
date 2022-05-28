package com.example.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserPersonalDataRepository extends JpaRepository<UserPersonalDataEntity, Long> {

    @Query("select upd "
            + "from UserPersonalDataEntity upd "
            + "where upd.lastNameTransliteration=:lastNameTransliteration")
    <S extends UserPersonalDataEntity> Optional<S> findByLastNameTransliteration(
            @Param("lastNameTransliteration") String lastNameTransliteration);

    @Query("select upd "
            + "from UserPersonalDataEntity as upd "
            + "inner join DetailUserEntity as dt on upd.id=dt.id "
            + "where dt.groupTransliteration=:groupTransliteration")
    List<UserPersonalDataEntity> getUserPersonalDataEntitiesByDetailUserEntityEqualsGroup(
            @Param("groupTransliteration") String groupTransliteration
    );
}
