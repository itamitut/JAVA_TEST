package ru.addressbook.tests;

import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTest2 extends TestBase {
    @Test   //(enabled = false)
    public void contactDeletionTest() throws InterruptedException {
        app.goTo().homePage();
        if(app.db().contacts().size() == 0){
            ContactData contact  = new ContactData().withFirstname("Имя" ).withLastname("Фамилия")
                    .withPhoneHome("34564390").withMobilePhone("5653213").withWorkPhone("9087667");
            app.contact().create(contact);
        }
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().closeAlert();
        app.goTo().homePage();
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size()-1);
        assertThat(after, equalTo(before.withOut(deletedContact)));
    }
}