package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;

/**
 * Created by Сергей on 14.04.2018.
 */
public class ContactModificationTest extends TestBase {
    @Test
    public void contactModificationTest(){
        app.getNavigationHelper().goToHomePage();
        if(! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact();
            app.getNavigationHelper().goToHomePage();
        }
        int before = app.getGroupHelper().getContactCount();
        app.getContactHelper().selectContact(before-1);
        app.getContactHelper().editContact();
        app.getContactHelper().fillNewContact(new ContactData("Tom","Olegovna","Frolova","yuric",
                "Mr","Cisco","Avenue","84955463217","8916549809","i@yandex.ru","friend of mine", null ),false);
        app.getContactHelper().updateContact();
        app.getNavigationHelper().goToHomePage();
        int after = app.getGroupHelper().getContactCount();
        Assert.assertEquals(after, before);
    }


}
