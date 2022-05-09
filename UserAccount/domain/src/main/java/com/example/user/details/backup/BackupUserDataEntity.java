package com.example.user.details.backup;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString
@Getter
@Setter
@Table(name = "backup_users_data")
public class BackupUserDataEntity {

    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @ToString.Exclude
    private Long id;

    private String phoneNumber;

    private String emailBackup;

}
