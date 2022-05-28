package com.example.user.details.backup;

import lombok.*;

import javax.persistence.*;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@Table(name = "backup_users_data")
public class BackupUserDataEntity {
    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @ToString.Exclude
    private Long id;

    private String phoneNumber;

    public BackupUserDataEntity(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
