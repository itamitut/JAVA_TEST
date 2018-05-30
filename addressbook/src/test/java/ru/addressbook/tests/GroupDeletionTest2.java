package ru.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;
import ru.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupDeletionTest2 extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        //Проверка условий через БД
        if (app.db().groups().size() == 0){
            app.group().create( new GroupData().withName("test1"));
        }
    }
    @Test
    public void testGroupDeletion2() {
        Groups before = app.db().groups();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        Groups after = app.db().groups();
        assertEquals(after.size(), before.size()-1);
        assertThat(after, equalTo(before.withOut(deletedGroup)));
    }
    
}
