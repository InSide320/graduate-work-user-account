package com.example.user.details;

import com.example.user.details.backup.BackupUserData;
import com.example.user.details.type.RoleType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "detail_users")
public class DetailUser {

    @Id
    @GeneratedValue
    private Integer id;
    private RoleType roleType;
    private LocalDate dateEnter;
    private LocalDate releaseDate;
    private String groupCyrillic;
    private String groupTransliteration;
    @Transient
    private BackupUserData backupUserData;

    @Override
    public String toString() {
        return "DetailUser{"
                + "id=" + id
                + ", roleType=" + roleType
                + ", dateEnter=" + dateEnter
                + ", releaseDate=" + releaseDate
                + ", groupCyrillic='" + groupCyrillic + '\''
                + ", groupTransliteration='" + groupTransliteration + '\''
                + ", backupUserData=" + backupUserData
                + '}';
    }
}
