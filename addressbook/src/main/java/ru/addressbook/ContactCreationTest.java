package ru.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTest extends TestBase{

    @Test
    public void ContactCreationTest() {

        goToAddContact();
        fillNewContact(new ContactData("Stepan","Stepanovich","Stepanov","itamitut",
                "Mr","Cisco","Avenue","84955463217","8916549809","i@yandex.ru","friend of mine"));
        submitNewContact();
    }

}
