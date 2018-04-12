package ru.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;

/**
 * Created by Сергей on 12.04.2018.
 */
public class GroupDeletionTest extends TestBase{
    @Test
    public void testGroupDeletion() {

        app.goToGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroup();

    }

}
