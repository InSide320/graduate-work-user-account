package com.example.user.details.introductory;

import com.example.exception.ValidationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
            long id) {

        if (ukLang.describeConstable().isEmpty()) {
            throw new ValidationException("Input ukrainian language for save");
        }
        if (mathSubject.describeConstable().isEmpty()) {
            throw new ValidationException("Input math subject for save");
        }
        if (additionalSubject.describeConstable().isEmpty()) {
            throw new ValidationException("Input additional subject for save");
        }
        if (ukLang < 1 || ukLang >= 13) {
            throw new IllegalArgumentException("The mark from the ukrainian language subject must be in the range from 1 to 12");
        }
        if (additionalSubject < 1 || additionalSubject > 13) {
            throw new IllegalArgumentException("The mark from the additional subject must be in the range from 1 to 12");
        }
        if (mathSubject < 1 || mathSubject > 13) {
            throw new IllegalArgumentException("The mark from the math subject must be in the range from 1 to 12");
        }

        List<Integer> estimatesSubject = new ArrayList<>(
                List.of(ukLang,
                        mathSubject,
                        additionalSubject)
        );

        return estimatesRepository
                .queryUpdateEstimatesEntityById(
                        ukLang, mathSubject, additionalSubject,
                        estimatesSubject.stream().mapToDouble(avg -> avg).average().getAsDouble(),
                        estimatesSubject.stream().mapToInt(Integer::intValue).min().getAsInt(),
                        estimatesSubject.stream().mapToInt(Integer::intValue).max().getAsInt(),
                        id);
    }

    public EstimatesEntity saveAndFlush(EstimatesEntity estimatesEntity) {
        return estimatesRepository.saveAndFlush(estimatesEntity);
    }
}
