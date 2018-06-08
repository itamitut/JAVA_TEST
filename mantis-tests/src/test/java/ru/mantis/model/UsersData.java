package ru.mantis.model;


public class UsersData {

    int id;
    String name;
    String email;



    @Override
    public String toString() {
        return "UsersData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


    public String getName() {
        return name;
    }
    public String getEmail() { return email; }
    public int getId() {
        return id;
    }
    public UsersData withId(int id) {this.id = id;
        return this;}
    public UsersData withName(String name) { this.name = name;
        return this;}
    public UsersData withEmail(String email) { this.email = email;
        return this;}
}
