package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest2 extends TestBase {
    @Test
    public void contactCreationTest2() {
        app.goTo().homePage();
        Contacts before = app.db().contacts();
        ContactData contact = new ContactData().withFirstname("НовоеИмя").withLastname("НоваяФамилия")
                .withMobilePhone("75776579").withPhoneHome("8777770").withWorkPhone("2222289");
        app.contact().create( contact );
        app.goTo().homePage();
        Contacts after = app.db().contacts();
        Assert.assertEquals( after.size(), before.size() + 1 );
        assertThat(after, equalTo(before.withAdded(contact)));

    }
}
