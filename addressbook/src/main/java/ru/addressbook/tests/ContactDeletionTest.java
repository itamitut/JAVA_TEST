package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by Сергей on 14.04.2018.
 */
public class ContactDeletionTest extends TestBase {
    @Test
    public void contactDeletionTest() {
        app.goToHomePage();
        if(! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().deleteContact(before.size()-1);
        app.getNavigationHelper().closeAlert();
        app.goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size()-1);
        before.remove(before.size()-1);
        Assert.assertEquals(after, before);
    }
}
