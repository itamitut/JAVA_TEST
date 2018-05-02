package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTest extends TestBase{

    @Test
    public void contactCreationTest() {
        app.getNavigationHelper().goToHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactCreation();
        ContactData contact  = new ContactData("Имя","Фамилия", "test1" );
        app.getContactHelper().fillNewContact(contact, true);
        app.getContactHelper().submitNewContact();
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size()+1);
        before.add(contact);

        Comparator<ContactData> lastAndFirstNameComp = (o1, o2) -> {
            //Сортируем по фамилии
            int flag =   o1.getLastname().compareTo(o2.getLastname());
            //если фамилии совпали, сортируем по имени:
            if (flag == 0) flag = o1.getFirstname().compareTo(o2.getFirstname());
            return flag;
        };
        before.sort(lastAndFirstNameComp);
        after.sort(lastAndFirstNameComp);
        Assert.assertEquals(after, before);
    }

}
