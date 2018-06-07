package ru.mantis.model;


import java.util.Objects;

public class UsersData {

    int id;
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
    public UsersData withId(int id) {this.id = id;
        return this;}
    public UsersData withName(String name) { this.name = name;
        return this;}
}
