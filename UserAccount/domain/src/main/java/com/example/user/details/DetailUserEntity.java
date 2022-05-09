package com.example.user.details;

import com.example.user.details.backup.BackupUserDataEntity;
import com.example.user.details.type.RoleType;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "detail_users")
public class DetailUserEntity {

    public DetailUserEntity(RoleType roleType,
                            LocalDate dateEnter,
                            LocalDate dateRelease,
                            String groupCyrillic,
                            String groupTransliteration,
                            BackupUserDataEntity backupUserDataEntity) {
        this.roleType = roleType;
        this.dateEnter = dateEnter;
        this.dateRelease = dateRelease;
        this.groupCyrillic = groupCyrillic;
        this.groupTransliteration = groupTransliteration;
        this.backupUserDataEntity = backupUserDataEntity;
    }

    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ToString.Exclude
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_type")
    private RoleType roleType;

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

}
