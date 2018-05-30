package ru.addressbook.tests;

import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTest2 extends TestBase{
    @Test
    public void contactModificationTest2()  {
        app.goTo().homePage();
        if(app.db().contacts().size() == 0){
            ContactData contact  = new ContactData().withFirstname("Name" ).withLastname("LastName")
                    .withMobilePhone("321456579").withPhoneHome("8755980").withWorkPhone("742389");
            app.contact().create(contact);
            app.goTo().homePage();
        }
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withFirstname("ДругоеИмя").withLastname("ДругаяФамилия")
                .withMobilePhone("321456579").withPhoneHome("8755980").withWorkPhone("742389");
        app.contact().modify( modifiedContact, contact );
        app.goTo().homePage();
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
    }

}
