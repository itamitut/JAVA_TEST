package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

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
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().editContact(before.size()-1);
        ContactData contact = new ContactData("Имя","Фамилия", "test1" );
        app.getContactHelper().fillNewContact(contact,false);
        app.getContactHelper().updateContact();
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
        before.remove(before.size()-1);
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
