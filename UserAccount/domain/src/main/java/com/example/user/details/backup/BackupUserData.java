package com.example.user.details.backup;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "backup_users_data")
public class BackupUserData {

    @Id
    @GeneratedValue
    private Integer id;
    private String phoneNumber;
    private String emailBackup;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BackupUserData that = (BackupUserData) o;
        return Objects.equals(id, that.id) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(emailBackup, that.emailBackup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phoneNumber, emailBackup);
    }

    @Override
    public String toString() {
        return "BackupUserData{"
                + "id=" + id
                + ", phoneNumber='" + phoneNumber + '\''
                + ", emailBackup='" + emailBackup + '\''
                + '}';
    }
}
