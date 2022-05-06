package com.example.user;

import com.example.user.credentials.CredentialUser;
import com.example.user.details.DetailUser;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users_personal_data")
@Entity
public class UserPersonalData {

    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "last_name_transliteration")
    private String lastNameTransliteration;

    @Column(name = "first_name_transliteration")
    private String firstNameTransliteration;

    @Column(name = "last_name")
    private String lastNameCyrillic;

    @Column(name = "first_name")
    private String firstNameCyrillic;

    @Column(name = "midl_name")
    private String midlNameCyrillic;

    @Transient
    private DetailUser detailUser;
    @Transient
    private CredentialUser credentialUser;


    @Override
    public String toString() {
        return "UserPersonalData{"
                + "id=" + id
                + ", lastNameTransliteration='" + lastNameTransliteration + '\''
                + ", firstNameTransliteration='"
                + firstNameTransliteration + '\''
                + ", lastNameCyrillic='" + lastNameCyrillic + '\''
                + ", firstNameCyrillic='" + firstNameCyrillic + '\''
                + ", midlNameCyrillic='" + midlNameCyrillic + '\''
                + ", detailUser=" + detailUser
                + ", credentialUser=" + credentialUser
                + '}';
    }
}
