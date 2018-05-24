package ru.addressbook.tests;

import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by Сергей on 14.04.2018.
 */
public class ContactModificationTest extends TestBase {
    @Test
    public void contactModificationTest()  {
        app.goTo().homePage();
        if(app.contact().all().size() == 0){
            ContactData contact  = new ContactData().withFirstname("Name" ).withLastname("LastName")
                    .withMobilePhone("321456579").withPhoneHome("8755980").withWorkPhone("742389");
            app.contact().create(contact);
            app.goTo().homePage();
        }
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withFirstname("ДругоеИмя").withLastname("ДругаяФамилия")
                .withMobilePhone("321456579").withPhoneHome("8755980").withWorkPhone("742389");
        app.contact().modify( modifiedContact, contact );
        app.goTo().homePage();
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
    }


}
