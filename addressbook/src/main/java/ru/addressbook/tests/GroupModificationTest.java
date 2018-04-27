package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by Сергей on 14.04.2018.
 */
public class GroupModificationTest extends TestBase {
    @Test
    public void testGroupModification() {
        app.goToGroupPage();
        if(! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test5","test5","test5"));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size()-1);
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test2", "test2", "test2"));
        app.getGroupHelper().submitGroupModification();
        app.goToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());
    }
}
