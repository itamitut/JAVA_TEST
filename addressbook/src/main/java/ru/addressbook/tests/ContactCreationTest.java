package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase{

    @Test
    public void contactCreationTest() {
        app.goTo().homePage();
        List<ContactData> before = app.contact().list();
        ContactData contact  = new ContactData().withFirstname("Имя").withLastname("Фамилия");
        app.contact().create( contact );
        app.goTo().homePage();
        List<ContactData> after = app.contact().list();
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
