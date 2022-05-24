package com.example.user.details.introductory;

import com.example.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;

@Service
public record EstimatesService(EstimatesRepository estimatesRepository) {

    public EstimatesEntity save(EstimatesEntity estimatesEntity) {
        return estimatesRepository.save(estimatesEntity);
    }

    public List<EstimatesEntity> getAll() {
        return estimatesRepository.findAll();
    }

    public EstimatesEntity getById(long id) {
        return estimatesRepository.getById(id);
    }

    public Integer updateEstimates(
            Integer ukLang,
            Integer mathSubject,
            Integer additionalSubject,
            Double avgSubject,
            Integer minSubject,
            Integer maxSubject,
            long id) {
        return estimatesRepository
                .queryUpdateEstimatesEntityById(
                        ukLang, mathSubject, additionalSubject,
                        avgSubject, minSubject, maxSubject,
                        id);
    }

    public Double getAvg(long id) {
        return estimatesRepository.getAvgSubject(id);
    }

    public EstimatesEntity saveAndFlush(EstimatesEntity estimatesEntity) {
        return estimatesRepository.saveAndFlush(estimatesEntity);
    }
}
