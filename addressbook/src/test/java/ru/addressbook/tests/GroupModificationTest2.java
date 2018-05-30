package ru.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;
import ru.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class GroupModificationTest2 extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
 //Проверка условий через БД
        if (app.db().groups().size() == 0){
            app.group().create( new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupModification2() {
    //Берем множество групп до и после из БД
        Groups before = app.db().groups();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId( modifiedGroup.getId() )
                .withName( "test1" ).withHeader( "test2" ).withFooter( "test3" );
        app.group().modify( group );
    //Проверяем, что в ГУИ тоже сходится
        assertThat( app.group().count(), equalTo( before.size()));
        Groups after = app.db().groups();
        assertThat( after, equalTo( before.withOut( modifiedGroup ).withAdded( group ) ) );

    }
}