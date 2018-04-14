package ru.addressbook.tests;

import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;

/**
 * Created by Сергей on 14.04.2018.
 */
public class ContactModificationTest extends TestBase {
    @Test
    public void contactModificationTest(){
        app.getContactHelper().selectContact();
        app.getContactHelper().editContact();
        app.getContactHelper().fillNewContact(new ContactData("Ulia","Olegovna","Frolova","yuric",
                "Mr","Cisco","Avenue","84955463217","8916549809","i@yandex.ru","friend of mine"));
        app.getContactHelper().updateNewContact();
        app.getNavigationHelper().goToHomePage();

    }


}
