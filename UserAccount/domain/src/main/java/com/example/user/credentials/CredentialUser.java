package com.example.user.credentials;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class CredentialUser {

    @Id
    @GeneratedValue
    private Integer id;
    private String authEmail;
    private String authPassword;

    public CredentialUser(Integer id, String authEmail, String authPassword) {
        this.id = id;
        this.authEmail = authEmail;
        this.authPassword = authPassword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthEmail() {
        return authEmail;
    }

    public void setAuthEmail(String authEmail) {
        this.authEmail = authEmail;
    }

    public String getAuthPassword() {
        return authPassword;
    }

    public void setAuthPassword(String authPassword) {
        this.authPassword = authPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CredentialUser that = (CredentialUser) o;
        return Objects.equals(id, that.id) && Objects.equals(authEmail, that.authEmail) && Objects.equals(authPassword, that.authPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authEmail, authPassword);
    }

    @Override
    public String toString() {
        return "CredentialsUser{"
                + "id=" + id
                + ", authEmail='" + authEmail + '\''
                + ", authPassword='" + authPassword + '\''
                + '}';
    }
}
