package ru.addressbook.tests;

import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.GroupData;

public class AddContactInGroup extends TestBase {
    @Test   //(enabled = false)
    public void createContactInGroupTest() {

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create( new GroupData().withName("test 1"));
        }
        if (app.db().contacts().size() == 0) {
            ContactData contact = new ContactData().withFirstname("Имя").withLastname("Фамилия");
            app.goTo().homePage();
            app.contact().create(contact);
        }
        ContactData contact = app.db().contacts().iterator().next();
        app.contact().selectContactById(contact.getId());
        GroupData selectedGroup = app.db().groups().iterator().next();
        app.contact().chooseGroup(selectedGroup);
    }
}
