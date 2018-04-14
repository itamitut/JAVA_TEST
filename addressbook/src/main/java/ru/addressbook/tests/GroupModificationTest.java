package ru.addressbook.tests;

import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;

/**
 * Created by Сергей on 14.04.2018.
 */
public class GroupModificationTest extends TestBase {
    @Test
    public void testGroupModification() {
        app.goToGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test2", "test2", "test2"));
        app.getGroupHelper().submitGroupModification();
        app.goToGroupPage();
    }
}
