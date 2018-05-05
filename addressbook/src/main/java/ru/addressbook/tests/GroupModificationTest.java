package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by Сергей on 14.04.2018.
 */
public class GroupModificationTest extends TestBase {
    @BeforeMethod
    public  void ensurePreconditions() {
        app.goTo().groupPage();
        if( app.group().list().size() == 0){
            app.group().create(new GroupData().withName("test2"));
        }
    }

    @Test
    public void testGroupModification() {

        List<GroupData> before = app.group().list();
        int index = before.size()-1;
        GroupData group = new GroupData().withId(before.get(index).getId())
                .withName("test1").withHeader("test2").withFooter("test3");
        app.group().modify( index, group );
        List<GroupData> after = app.group().list();
        assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(group);
        Comparator<? super GroupData> byId = Comparator.comparingInt( GroupData::getId );
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }

}
