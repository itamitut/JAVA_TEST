package ru.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;
import ru.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ContactCreationTest extends TestBase{
    //  Данные из файла json
    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader( new FileReader( new File( "src/test/resources/contacts.json" )))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> groups = gson.fromJson( json, new TypeToken<List<ContactData>>() {
            }.getType() );
            return groups.stream().map( (g) -> new Object[]{g} ).collect( Collectors.toList() ).iterator();
        }
    }
    @Test(dataProvider = "validContactsFromJson")
    public void contactCreationTest(ContactData contact) {
        app.goTo().homePage();
        Contacts before = app.contact().all();
//        File photo = new File("src/test/resources/stru.png");

        app.contact().create(contact);
        app.goTo().homePage();
        Contacts after = app.contact().all();
        Assert.assertEquals(after.size(), before.size()+1);
        before.add(contact);
    //    Assert.assertEquals(after, before);
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
