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
        app.goTo().homePage();
        if(app.contact().list().size() == 0){
            ContactData contact  = new ContactData().withFirstname("Имя" ).withLastname("Фамилия");
            app.contact().create(contact);
            app.goTo().homePage();
        }
        List<ContactData> before = app.contact().list();
        app.contact().editContact(before.size()-1);
        ContactData contact = new ContactData().withFirstname("Имя" ).withLastname("Фамилия").withGroup("test1");
        app.contact().fillNewContact(contact,false);
        app.contact().updateContact();
        app.goTo().homePage();
        List<ContactData> after = app.contact().list();
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
