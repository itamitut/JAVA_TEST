package ru.addressbook.tests;

import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;
import ru.addressbook.model.GroupData;

public class ContactDeletionFromGroup extends TestBase {

    @Test   //(enabled = false)
    public void contactDeletionFromGroupTest() {

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create( new GroupData().withName("test 1"));
        }
        if (app.db().contacts().size() == 0) {
            ContactData contact = new ContactData().withFirstname("Имя").withLastname("Фамилия")
                    .withGroup(app.db().groups().iterator().next());
            app.goTo().homePage();
            app.contact().create( contact);
        }
        GroupData  selectedGroup = app.db().groups().iterator().next();
            app.goTo().homePage();
            app.contact().chooseGroup(selectedGroup);
            Contacts contacts = app.contact().all();
    //Ищем группу, в которой есть хоть один котакт:
            while (contacts.size() == 0){
                selectedGroup = app.db().groups().iterator().next();
                app.contact().chooseGroup(selectedGroup);
                contacts = app.contact().all();
            }
        System.out.println("Непустая группа найдена");
            ContactData deletedContact = contacts.iterator().next();
            app.contact().deleteFromGroup(deletedContact);
            app.goTo().homePage();
    }
}



