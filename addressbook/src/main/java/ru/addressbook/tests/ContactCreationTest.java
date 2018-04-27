package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.GroupData;

public class ContactCreationTest extends TestBase{

    @Test
    public void contactCreationTest() {
        app.getNavigationHelper().goToHomePage();
        int before = app.getGroupHelper().getContactCount();
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillNewContact(new ContactData("Robert","Stepanovich","Zizi","yuric",
                "Mr","Cisco","Avenue","84955463217","8916549809","i@yandex.ru","friend of mine", "test1" ),true);
        app.getContactHelper().submitNewContact();
        app.getNavigationHelper().goToHomePage();
        int after = app.getGroupHelper().getContactCount();
        Assert.assertEquals(after, before+1);
    }

}
