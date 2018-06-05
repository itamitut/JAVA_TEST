package ru.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;
import ru.addressbook.model.GroupData;
import ru.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroup extends TestBase {

    @BeforeMethod
    public void preconditions() {
        //Проверяем предусловия и в случае отсутствия создаем:
        if (app.db().groups().isEmpty()) {
            app.goTo().groupPage();
            app.group().create( new GroupData().withName( "test 1" ) );
        }
        if (app.db().contacts().isEmpty()) {
            ContactData contact = new ContactData().withFirstname( "Имя" ).withLastname( "Фамилия" );
            app.goTo().homePage();
            app.contact().create( contact );
        }
    }
    @Test
    public void addContactToGroupTest() {

        ContactData addedContact; // Добавляемый контакт
        GroupData group = null; //Группа, в которую будем добавлять
        Groups groupBefore;
        Groups groupAfter = new Groups();
        int id; //ID  контакта

        //Если все контакты записан во все группы, удалим из одной:
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        boolean flag = true;
        for (ContactData contact : contacts) {
            if (!contact.getGroups().equals(groups)) flag = false;
        }
        if(flag){
            ContactData con =  app.db().contacts().iterator().next();
            GroupData grp = con.getGroups().iterator().next();
            app.goTo().homePage();
            app.contact().chooseGroup(grp);
            app.contact().deleteFromGroup(con);
        }
        //Найдем контакт, который не записан во все группы:
        contacts = app.db().contacts();
        groups = app.db().groups();
        for (ContactData contact : contacts) {
           if (contact.getGroups().equals(groups)){ continue;}
           addedContact = contact;
            id = addedContact.getId();
           groupBefore = addedContact.getGroups();
       // Выберем группу, в которую будем его записывать:
        for (GroupData gr : groups){
               if (addedContact.getGroups().contains(gr)){ continue;}
                group = gr;
               break;
           }
           app.contact().initContactCreation();
           app.goTo().homePage();
           app.contact().selectContactById(id);
           app.contact().addToGroup(group);
            //Ищем удаленный контакт по ID и берем его группы:
            contacts = app.db().contacts();
            for (ContactData cont : contacts) {
                if (cont.getId() == id) {
                    addedContact = cont;
                    groupAfter = addedContact.getGroups();
                    break;
                }
            }
           assertThat(groupBefore.withAdded(group), equalTo(groupAfter));
        }
    }
}
