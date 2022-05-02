package com.example.user.details.backup;

import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Objects;

@Component
public class BackupUserData {

    @Id
    @GeneratedValue
    private Integer id;
    private String phoneNumber;
    private String emailBackup;

    public BackupUserData(Integer id, String phoneNumber, String emailBackup) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.emailBackup = emailBackup;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailBackup() {
        return emailBackup;
    }

    public void setEmailBackup(String emailBackup) {
        this.emailBackup = emailBackup;
    }

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
