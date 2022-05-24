package com.example.user;

import com.example.user.credentials.CredentialUserEntity;
import com.example.user.details.DetailUserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@Table(name = "users_personal_data")
@Entity
public class UserPersonalDataEntity {

    public UserPersonalDataEntity(
            Long id, String lastNameTransliteration,
            String firstNameTransliteration,
            String lastNameCyrillic,
            String firstNameCyrillic,
            String midlNameCyrillic,
            DetailUserEntity detailUserEntity,
            CredentialUserEntity credentialUserEntity) {
        this.id = id;
        this.lastNameTransliteration = lastNameTransliteration;
        this.firstNameTransliteration = firstNameTransliteration;
        this.lastNameCyrillic = lastNameCyrillic;
        this.firstNameCyrillic = firstNameCyrillic;
        this.midlNameCyrillic = midlNameCyrillic;
        this.detailUserEntity = detailUserEntity;
        this.credentialUserEntity = credentialUserEntity;
    }

    @Id
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToOne
    @JoinTable(name = "detail_users",
            joinColumns = @JoinColumn(name = "id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id", referencedColumnName = "id")
    )

    @Getter
    @Setter
    private DetailUserEntity detailUserEntity;

    @OneToOne
    @JoinTable(name = "credential_users",
            joinColumns = @JoinColumn(name = "id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id", referencedColumnName = "id"))
    private CredentialUserEntity credentialUserEntity;

    @Override
    public String toString() {
        return "UserPersonalDataEntity{"
                + "id=" + id
                + ", lastNameTransliteration='" + lastNameTransliteration + '\''
                + ", firstNameTransliteration='" + firstNameTransliteration + '\'' + "\n"
                + ", lastNameCyrillic='" + lastNameCyrillic + '\''
                + ", firstNameCyrillic='" + firstNameCyrillic + '\''
                + ", midlNameCyrillic='" + midlNameCyrillic + '\'' + "\n"
                + ", detailUserEntity=" + detailUserEntity + "\n"
                + '}';
    }
}
