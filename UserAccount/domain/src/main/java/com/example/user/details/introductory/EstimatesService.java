package com.example.user.details.introductory;

import com.example.exception.NotFoundException;
import com.example.exception.ValidationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public record EstimatesService(EstimatesRepository estimatesRepository) {

    public EstimatesEntity save(EstimatesEntity estimatesEntity) {
        if (estimatesEntity == null) {
            throw new NullPointerException("Input null data");
        }
        return estimatesRepository.save(estimatesEntity);
    }

    public Integer updateEstimates(
            Integer ukLang,
            Integer mathSubject,
            Integer additionalSubject,
            long id) {

        if (ukLang == null) {
            throw new NullPointerException("Input ukrainian language for save");
        }
        if (mathSubject == null) {
            throw new NullPointerException("Input math subject for save");
        }
        if (additionalSubject == null) {
            throw new NullPointerException("Input additional subject for save");
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

        if (estimatesRepository.findAll().size() < id) {
            throw new NotFoundException("Not found estimates id");
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
