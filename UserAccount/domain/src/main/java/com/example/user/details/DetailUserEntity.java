package com.example.user.details;

import com.example.user.details.backup.BackupUserDataEntity;
import com.example.user.details.introductory.EstimatesEntity;
import com.example.user.credentials.role.RoleType;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "detail_users")
public class DetailUserEntity implements Serializable {

    public DetailUserEntity(LocalDate dateEnter,
                            LocalDate dateRelease,
                            String groupCyrillic,
                            String groupTransliteration,
                            BackupUserDataEntity backupUserDataEntity,
                            EstimatesEntity estimates) {
        this.dateEnter = dateEnter;
        this.dateRelease = dateRelease;
        this.groupCyrillic = groupCyrillic;
        this.groupTransliteration = groupTransliteration;
        this.backupUserDataEntity = backupUserDataEntity;
        this.estimatesEntity = estimates;
    }

    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ToString.Exclude
    private Long id;

    @Column(name = "date_enter")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateEnter;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_release")
    private LocalDate dateRelease;

    @Column(name = "group_cyrillic")
    private String groupCyrillic;

    @Column(name = "group_transliteration")
    private String groupTransliteration;

    @OneToOne
    @JoinTable(name = "backup_users_data",
            joinColumns = @JoinColumn(name = "id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id", referencedColumnName = "id")
    )
    private BackupUserDataEntity backupUserDataEntity;

    @ToString.Exclude
    @OneToOne
    @JoinTable(name = "student_entrance_scores",
            joinColumns = @JoinColumn(name = "id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id", referencedColumnName = "id"))
    private EstimatesEntity estimatesEntity;
}
