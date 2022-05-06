package com.example.user.credentials;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "credential_users")
public class CredentialUser {

    @Id
    @GeneratedValue
    private Integer id;
    private String authEmail;
    private String authPassword;

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
