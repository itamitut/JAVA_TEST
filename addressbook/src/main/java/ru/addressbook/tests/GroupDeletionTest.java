package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by Сергей on 12.04.2018.
 */
public class GroupDeletionTest extends TestBase{
    @BeforeMethod
    public  void ensurePreconditions() {
        app.goTo().groupPage();
        if( app.group().list().size() == 0){
            app.group().create(new GroupData().withName("test2"));
        }
    }

    @Test
    public void testGroupDeletion() {

        List<GroupData> before = app.group().list();
        int index = before.size()-1;
        app.group().delete( index );
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size()-1);

        before.remove(index);
        Assert.assertEquals(before,after);
    }



}
