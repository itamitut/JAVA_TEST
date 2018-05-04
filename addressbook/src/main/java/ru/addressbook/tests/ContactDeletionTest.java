package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by Сергей on 14.04.2018.
 */
public class ContactDeletionTest extends TestBase {
    @Test(enabled = false)
    public void contactDeletionTest() {
        app.goTo().homePage();
        if(app.contact().list().size() == 0){
            ContactData contact  = new ContactData("Имя","Фамилия", "test1" );
            app.contact().create(contact);
        }
        List<ContactData> before = app.contact().list();
        app.contact().deleteContact(before.size()-1);
        app.goTo().closeAlert();
        app.goTo().homePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size()-1);
        before.remove(before.size()-1);
        Assert.assertEquals(after, before);
    }
}
