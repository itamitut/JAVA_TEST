package ru.addressbook.tests;

import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;

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
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroup();
        app.goToHomePage();
    }

}
