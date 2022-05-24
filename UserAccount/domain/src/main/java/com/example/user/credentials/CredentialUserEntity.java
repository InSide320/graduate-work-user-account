package com.example.user.credentials;

import com.example.user.credentials.role.RoleType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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

    public CredentialUserEntity(String authEmail, String authPassword, RoleType roleType) {
        this.authEmail = authEmail;
        this.authPassword = authPassword;
        this.roleType = roleType;
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
