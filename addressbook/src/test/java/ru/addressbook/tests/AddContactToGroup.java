package ru.addressbook.tests;

import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;
import ru.addressbook.model.GroupData;
import ru.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroup extends TestBase {
    @Test   //(enabled = false)
    public void addContactToGroupTest() {
        //Проверяем предусловия и в случае отсутствия создаем:
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create( new GroupData().withName("test 1"));
        }
        if (app.db().contacts().size() == 0) {
            ContactData contact = new ContactData().withFirstname("Имя").withLastname("Фамилия");
            app.goTo().homePage();
            app.contact().create(contact);
        }
        //Выбираем контакт и группу из DB:
        ContactData contact = app.db().contacts().iterator().next();
    //Найдем группу, в которой нет этого контакта и добавим в нее:
        Groups groups = app.db().groups();
       for (GroupData group : groups) {
           if (contact.getGroups().contains(group)){ continue;}
           app.goTo().homePage();
           app.contact().chooseGroup(group);
           Contacts contactsBefore = app.contact().all();
    // Здесь бага приложения, приходится делать лишнее действие:
            app.contact().initContactCreation();
           app.goTo().homePage();
           app.contact().selectContactById( contact.getId());
           app.contact().addToGroup(group);
           //Проверяем, что в группе появился контакт:
           app.goTo().homePage();
           app.contact().chooseGroup(group);
           Contacts contactsАfter = app.contact().all();
               assertThat(contactsBefore.withAdded(contact), equalTo(contactsАfter));
           break;
        }
    }
}
