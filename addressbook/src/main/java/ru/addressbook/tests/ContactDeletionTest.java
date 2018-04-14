package ru.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by Сергей on 14.04.2018.
 */
public class ContactDeletionTest extends TestBase {
    @Test
    public void ContactCreationTest() {
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().closeAlert();
        app.goToHomePage();
    }
}
