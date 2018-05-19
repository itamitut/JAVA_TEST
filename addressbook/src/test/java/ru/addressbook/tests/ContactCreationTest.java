package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;

import java.io.File;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase{

    @Test
    public void contactCreationTest() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        File photo = new File("src/test/resources/stru.png");
        ContactData contact  = new ContactData().withFirstname("Имя").withLastname("Фамилия")
                .withMobilePhone("321456579").withPhoneHome("8755980").withWorkPhone("742389")
                .withPhoto(photo);
        app.contact().create( contact );
        app.goTo().homePage();
        Contacts after = app.contact().all();
        Assert.assertEquals(after.size(), before.size()+1);
        before.add(contact);
        Assert.assertEquals(after, before);
/* Сравнение сортированных списков
        Comparator<ContactData> lastAndFirstNameComp = (o1, o2) -> {
            //Сортируем по фамилии
            int flag =   o1.getLastname().compareTo(o2.getLastname());
            //если фамилии совпали, сортируем по имени:
            if (flag == 0) flag = o1.getFirstname().compareTo(o2.getFirstname());
            return flag;
        };
        before.sort(lastAndFirstNameComp);
        after.sort(lastAndFirstNameComp);
*/

    }
/*    @Test
    public void testFile(){
        File photo = new File("src/test/resources/stru.png");
        System.out.println( photo.exists() );
    }
*/
}