package ru.addressbook.model;

public class ContactData {
    private  String firstname;
    private  String middlename;
    private  String lastname;
    private  String nickname;
    private  String title;
    private  String company;
    private  String address;
    private  String home;
    private  String mobile;
    private  String email;
    private  String notes;
    private  String group;
    
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
    public ContactData withHome(String home) {
        this.home = home;
        return this;}
    public ContactData withMobile(String mobile) {
        this.mobile = mobile;
        return this;}
    public ContactData withEmail(String email) {
        this.email = email;
        return this;}
    public ContactData withNotes(String notes) {
        this.notes = notes;
        return this;}
    public ContactData withGroup(String group) {
        this.group = group;
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
    public String getHome() {
        return home;}
    public String getMobile() {
        return home;}
    public String getEmail() {
        return email;}
    public String getNotes() {
        return home;}
    public String getGroup() {
        return firstname;}





}