package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

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
        int before = app.getGroupHelper().getContactCount();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().closeAlert();
        app.goToHomePage();
        int after = app.getGroupHelper().getContactCount();
        Assert.assertEquals(after, before-1);
    }
}
