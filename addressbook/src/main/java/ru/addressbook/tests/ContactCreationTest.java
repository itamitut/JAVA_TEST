package ru.addressbook.tests;

import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase{

    @Test
    public void ContactCreationTest() {

        app.goToAddContact();
        app.getContactHelper().fillNewContact(new ContactData("Alex","Stepanovich","Afonov","yuric",
                "Mr","Cisco","Avenue","84955463217","8916549809","i@yandex.ru","friend of mine"));
        app.getContactHelper().submitNewContact();
        app.getNavigationHelper().goToHomePage();
    }

}
