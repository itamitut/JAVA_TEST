package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by Сергей on 12.04.2018.
 */
public class GroupDeletionTest extends TestBase{
    @Test
    public void testGroupDeletion() {
        app.goToGroupPage();
        if(! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test5","test5","test5"));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size()-1);
        app.getGroupHelper().deleteSelectedGroup();
        app.goToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size()-1);
    }

}
