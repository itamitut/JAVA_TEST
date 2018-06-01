package ru.addressbook.tests;

import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;
import ru.addressbook.model.GroupData;
import ru.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionFromGroup extends TestBase {

    @Test   //(enabled = false)
    public void contactDeletionFromGroupTest() {

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create( new GroupData().withName("test 1"));
        }
        if (app.db().contacts().size() == 0) {
            ContactData contact = new ContactData().withFirstname("Имя").withLastname("Фамилия")
                    .withGroup(app.db().groups().iterator().next());
            app.goTo().homePage();
            app.contact().create( contact);
        }
     // Выбираем множество групп из BD:
        Set<GroupData> groups = app.db().groups();
    //Выбираем группу, в которой есть хоть один котакт:
        for (GroupData group : groups){
            app.goTo().homePage();
            app.contact().chooseGroup(group);
            Contacts contactsBefore = app.contact().all();
            if(contactsBefore.size() >0) //Если найдена группа с контактами, выбираем контакт и удаляем из группы
                {ContactData deletedContact = contactsBefore.iterator().next();
                app.contact().deleteFromGroup(deletedContact);
    //Проверяем, что в группе нет больше удаленного контакта
                app.goTo().homePage();
                app.contact().chooseGroup(group);
                Contacts contactsAfter = app.contact().all();
                assertThat(contactsAfter.withAdded(deletedContact), equalTo(contactsBefore));

                break;
            }
        }
    }
}



