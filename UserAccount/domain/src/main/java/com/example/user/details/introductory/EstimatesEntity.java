package com.example.user.details.introductory;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "student_entrance_scores")
public class EstimatesEntity {
    @Id
    @Column(name = "id", nullable = false)
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Exclude
    private Long id;
    @Column(name = "ukrainian_language")
    Integer ukrainianLanguage;
    @Column(name = "math_subject")
    Integer mathSubject;
    @Column(name = "additional_subject")
    Integer additionalSubject;
    @Column(name = "avg_subject")
    Double avgSubject;
    @Column(name = "min_subject")
    Integer minSubject;
    @Column(name = "max_subject")
    Integer maxSubject;


    public EstimatesEntity(
            Integer ukrainianLanguage,
            Integer mathSubject,
            Integer additionalSubject) {
        this.ukrainianLanguage = ukrainianLanguage;
        this.mathSubject = mathSubject;
        this.additionalSubject = additionalSubject;
    }

    @Override
    public String toString() {
        return "EstimatesEntity{"
                + ", ukrainianLanguage=" + ukrainianLanguage + "\t"
                + ", mathSubject=" + mathSubject + "\t"
                + ", additionalSubject=" + additionalSubject + "\t"
                + ", avgSubject=" + avgSubject + "\n"
                + ", minSubject=" + minSubject + "\n"
                + ", maxSubject=" + maxSubject + "\n"
                + '}';
    }
}
