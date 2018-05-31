package ru.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "addressbook")
public class ContactData {

//  @Expose - поля, входящие в json,  @XStreamOmitField - НЕ входящие в XML

    @Id
    @Column(name = "id")
    @XStreamOmitField
    private int id = Integer.MAX_VALUE;


    @Column(name = "firstname")
    @Expose
    private  String firstname;

    @Transient
    @XStreamOmitField
    private  String middlename;

    @Expose
    @Column(name = "lastname")
    private  String lastname;

    @Transient
    @XStreamOmitField
    private  String nickname;

    @Transient
    @XStreamOmitField
    private  String title;

    @Transient
    @XStreamOmitField
    private  String company;

    @Transient
    @XStreamOmitField
    private  String address;

    @Transient
    @XStreamOmitField
    private  String allPhones;

    @Column(name = "home")
    @Type(type="text")
    @Expose
    private  String homePhone;

    @Column(name = "mobile")
    @Type(type="text")
    @Expose
    private  String mobilePhone;

    @Column(name = "work")
    @Type(type="text")
    @Expose
    private  String workPhone;

    @Transient
    @XStreamOmitField
    private  String allEmails;

    @Transient
    @Expose
    private  String email;

    @Transient
    @XStreamOmitField
    private  String email2;

    @Transient
    @XStreamOmitField
    private  String email3;

    @Transient
    @XStreamOmitField
    private  String notes;

    @Transient
    @Column(name = "photo")
    @Type(type="text")
    @Expose
    private String photo;



    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<>();

    public ContactData withId(int id) {this.id = id;
        return this;}
    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;}
    public ContactData withMiddlename(String middlename) {
        this.middlename = middlename;
        return this;}
    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;}
    public ContactData withNickname(String nickname) {
        this.nickname = nickname;
        return this;}
    public ContactData withTitle(String title) {
        this.title = title;
        return this;}
    public ContactData withCompany(String company) {
        this.company = company;
        return this;}
    public ContactData withAddress(String address) {
        this.address = address;
        return this;}
    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;}
    public ContactData withPhoneHome(String homePhone) {
        this.homePhone = homePhone;
        return this;}
    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;}
    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;}
    public ContactData withEmail(String email) {
        this.email = email;
        return this;}
    public ContactData withEmail2(String email) {
        this.email2 = email;
        return this;}
    public ContactData withEmail3(String email) {
        this.email3 = email;
        return this;}
    public ContactData withAllEmails(String email) {
        this.allEmails = email;
        return this;}
    public ContactData withNotes(String notes) {
        this.notes = notes;
        return this;}
    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;}
    public ContactData withGroup(GroupData group) {
        this.groups.add(group);
        return this;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (firstname != null ? !firstname.equals( that.firstname ) : that.firstname != null) return false;
        return lastname != null ? lastname.equals( that.lastname ) : that.lastname == null;
    }

    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
    public int getId() {return id;}
    public String getFirstname() {
        return firstname;}
    public String getMiddlename() {
        return middlename;}
        public String getLastname() {
        return lastname;}
    public String getNickname() {
        return nickname;}
    public String getTitle() {
        return title;}
    public String getCompany() {
        return company;}
    public String getAddress() {
        return address;}
    public String getAllPhones() {
        return allPhones;}
    public String getHomePhone() {
        return homePhone;}
    public String getMobilePhone() {
        return mobilePhone;}
    public String getWorkPhone() {
        return workPhone;}
    public String getEmail() {
        return email;}
    public String getEmail2() {
        return email2;}
    public String getEmail3() {
        return email3;}
    public String getAllEmails() {
        return allEmails;}
    public String getNotes() {
        return notes;}

    public Groups getGroups() {
        return new Groups(groups);
    }

    public File getPhoto() {return new File(photo);}

    public ContactData inGroup(GroupData group) {
        groups.add( group );
        return this;
    }
}