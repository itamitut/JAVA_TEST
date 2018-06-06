package ru.mantis.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "mantis_user_table")
public class UsersData {

    @Id
    @Column(name = "id")
    int id;

    @Column(name = "username")
    @Type(type="text")
    String name;

    @Override
    public String toString() {
        return "UsersData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersData user = (UsersData) o;
        return id == user.id &&
                Objects.equals( name, user.name );
    }

    @Override
    public int hashCode() {

        return Objects.hash( id, name );
    }

    public String getName() {

        return name;
    }

    public int getId() {

        return id;
    }

    public UsersData(int id, String name){
        this.id = id;
        this.name = name;
    }
}
