package com.example.user.details.introductory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.OptionalDouble;
import java.util.OptionalInt;

@Repository
public interface EstimatesRepository extends JpaRepository<EstimatesEntity, Long> {

    @Modifying
    @Transactional
    @Query("update EstimatesEntity set "
            + "ukrainianLanguage=:ukLang, "
            + "mathSubject=:mathSubject, "
            + "additionalSubject=:additionalSubject, "
            + "avgSubject=:avgSubject, "
            + "minSubject=:minSubject, "
            + "maxSubject=:maxSubject "
            + "where id=:id")
    Integer queryUpdateEstimatesEntityById(
            @Param("ukLang") Integer ukLang,
            @Param("mathSubject") Integer mathSubject,
            @Param("additionalSubject") Integer additionalSubject,
            @Param("avgSubject") Double avgSubject,
            @Param("minSubject") Integer minSubject,
            @Param("maxSubject") Integer maxSubject,
            @Param("id") long id);

    @Query("select (st.ukrainianLanguage + st.mathSubject + st.additionalSubject) / 3 as sumSubject "
            + "from EstimatesEntity as st "
            + "where st.id = :id "
            + "group by st.ukrainianLanguage + st.mathSubject + st.additionalSubject")
    Double getAvgSubject(@Param("id") long id);
}
