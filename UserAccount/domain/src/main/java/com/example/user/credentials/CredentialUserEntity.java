package com.example.user.credentials;

import lombok.*;

import javax.persistence.*;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "credential_users")
public class CredentialUserEntity {

    public CredentialUserEntity(String authEmail, String authPassword) {
        this.authEmail = authEmail;
        this.authPassword = authPassword;
    }

    @Id
    @Setter(AccessLevel.PROTECTED)
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ToString.Exclude
    private Long id;

    @Column(name = "authorization_email")
    private String authEmail;

    @Column(name = "authorization_password")
    private String authPassword;

}
