package com.example.user.details;

import com.example.user.details.type.RoleType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class DetailUser {

    @Id
    @GeneratedValue
    private Integer id;
    private RoleType roleType;
    private LocalDate dateEnter;
    private LocalDate releaseDate;
    private String groupUk;
    private String groupTransliteration;

    public DetailUser(
            Integer id,
            RoleType roleType,
            LocalDate dateEnter, LocalDate releaseDate,
            String groupUk, String groupTransliteration) {
        this.id = id;
        this.roleType = roleType;
        this.dateEnter = dateEnter;
        this.releaseDate = releaseDate;
        this.groupUk = groupUk;
        this.groupTransliteration = groupTransliteration;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public LocalDate getDateEnter() {
        return dateEnter;
    }

    public void setDateEnter(LocalDate dateEnter) {
        this.dateEnter = dateEnter;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGroupUk() {
        return groupUk;
    }

    public void setGroupUk(String groupUk) {
        this.groupUk = groupUk;
    }

    public String getGroupTransliteration() {
        return groupTransliteration;
    }

    public void setGroupTransliteration(String groupTransliteration) {
        this.groupTransliteration = groupTransliteration;
    }

    @Override
    public String toString() {
        return "DetailsUser{"
                + "id=" + id
                + ", roleType=" + roleType
                + ", dateEnter=" + dateEnter
                + ", releaseDate=" + releaseDate
                + ", groupUk='" + groupUk + '\''
                + ", groupTransliteration='" + groupTransliteration + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailUser that = (DetailUser) o;
        return Objects.equals(id, that.id)
                && roleType == that.roleType && Objects.equals(dateEnter, that.dateEnter)
                && Objects.equals(releaseDate, that.releaseDate)
                && Objects.equals(groupUk, that.groupUk)
                && Objects.equals(groupTransliteration, that.groupTransliteration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id, roleType,
                dateEnter, releaseDate,
                groupUk, groupTransliteration
        );
    }
}
