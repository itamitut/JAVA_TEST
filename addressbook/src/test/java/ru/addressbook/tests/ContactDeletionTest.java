package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by Сергей on 14.04.2018.
 */
public class ContactDeletionTest extends TestBase {
    @Test   //(enabled = false)
    public void contactDeletionTest() throws InterruptedException {
        app.goTo().homePage();
        if(app.contact().all().size() == 0){
            ContactData contact  = new ContactData().withFirstname("Имя" ).withLastname("Фамилия");
            app.contact().create(contact);
        }
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().closeAlert();
        app.goTo().homePage();
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size()-1);
        assertThat(after, equalTo(before.withOut(deletedContact)));
    }
}
