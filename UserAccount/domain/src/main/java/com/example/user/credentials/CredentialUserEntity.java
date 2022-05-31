package com.example.user.credentials;

import com.example.user.credentials.role.RoleType;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

@Entity
@ToString
@Setter
@Getter
@NoArgsConstructor
@Table(name = "credential_users")
public class CredentialUserEntity implements UserDetails, Serializable {

    public CredentialUserEntity(String authEmail, String authPassword,
                                RoleType roleType, String backupEmail) {
        this.authEmail = authEmail;
        this.authPassword = authPassword;
        this.roleType = roleType;
        this.backupEmail = backupEmail;
    }

    @Override
    public String toString() {
        return "CredentialUserEntity{"
                + ", authEmail='" + authEmail + '\'' + "\n"
                + ", authPassword='" + authPassword + '\'' + "\n"
                + ", roleType=" + roleType + "\t"
                + ", backupEmail='" + backupEmail + '\'' + "\n"
                + '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ToString.Exclude
    private Long id;

    @Column(name = "authorization_email")
    private String authEmail;

    @ToString.Exclude
    @Column(name = "authorization_password")
    private String authPassword;

    @Column(name = "role_type")
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Column(name = "email_backup")
    private String backupEmail;

    @ToString.Exclude
    @Column(name = "activation_code")
    private String activationCode;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(getRoleType());
    }

    @Override
    public String getPassword() {
        return authPassword;
    }

    @Override
    public String getUsername() {
        return authEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
