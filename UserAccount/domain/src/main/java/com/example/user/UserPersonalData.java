package com.example.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class UserPersonalData implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String lastNameTransliteration;
    private String firstNameTransliteration;
    private String lastNameUk;
    private String firstNameUk;
    private String midlNameUk;

    public UserPersonalData() {
    }

    public UserPersonalData(
            Integer id, String lastNameTransliteration,
            String firstNameTransliteration,
            String lastNameUk, String firstNameUk,
            String midlNameUk
    ) {
        this.id = id;

        this.lastNameTransliteration = lastNameTransliteration;
        this.firstNameTransliteration = firstNameTransliteration;

        this.lastNameUk = lastNameUk;
        this.firstNameUk = firstNameUk;
        this.midlNameUk = midlNameUk;
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", lastNameEng='" + lastNameTransliteration + '\''
                + ", firstNameEng='" + firstNameTransliteration + '\''
                + ", lastNameUk='" + lastNameUk + '\''
                + ", firstNameUk='" + firstNameUk + '\''
                + ", midlNameUk='" + midlNameUk + '\''
                + '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastNameTransliteration() {
        return lastNameTransliteration;
    }

    public void setLastNameTransliteration(String lastNameTransliteration) {
        this.lastNameTransliteration = lastNameTransliteration;
    }

    public String getFirstNameTransliteration() {
        return firstNameTransliteration;
    }

    public void setFirstNameTransliteration(String firstNameTransliteration) {
        this.firstNameTransliteration = firstNameTransliteration;
    }

    public String getLastNameUk() {
        return lastNameUk;
    }

    public void setLastNameUk(String lastNameUk) {
        this.lastNameUk = lastNameUk;
    }

    public String getFirstNameUk() {
        return firstNameUk;
    }

    public void setFirstNameUk(String firstNameUk) {
        this.firstNameUk = firstNameUk;
    }

    public String getMidlNameUk() {
        return midlNameUk;
    }

    public void setMidlNameUk(String midlNameUk) {
        this.midlNameUk = midlNameUk;
    }

}
