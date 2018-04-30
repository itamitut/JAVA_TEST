package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import static org.testng.Assert.assertEquals;

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
        GroupData group = new GroupData(before.get(before.size()-1).getId(), "test7", "test2", "test2");
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitGroupModification();
        app.goToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        assertEquals(after.size(), before.size());
        before.remove(before.size()-1);
        before.add(group);
        Comparator<? super GroupData> byId = Comparator.comparingInt( GroupData::getId );
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }
}
