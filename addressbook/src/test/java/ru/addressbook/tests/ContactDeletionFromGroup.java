package ru.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;
import ru.addressbook.model.GroupData;
import ru.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionFromGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        // Создаем группу, если нет ни одной:
        if (app.db().groups().isEmpty()) {
            app.goTo().groupPage();
            app.group().create( new GroupData().withName("test 1"));
        }
        // Создаем контакт, входящий в существующую группу, если нет ни одного:
        if (app.db().contacts().isEmpty()) {
            ContactData newContact = new ContactData().withFirstname( "Имя" ).withLastname( "Фамилия" )
                    .withGroup( app.db().groups().iterator().next() );
            app.goTo().homePage();
            app.contact().create( newContact );
        }
    }
    @Test
    public void contactDeletionFromGroupTest() {
        ContactData deletedContact = null; // Удаляемый контакт
        GroupData group = null; //Группа, из которой будем удалять
        int id =0; //ID удаляемого контакта
        Groups groupAfter = new Groups();//Группы контакта после удаления

        // Выбираем контакт, входящий хотя бы в одну группу:
        Contacts contacts = app.db().contacts();
        for (ContactData contact : contacts) {
            if (!contact.getGroups().isEmpty()) {
                deletedContact = contact;
                id = deletedContact.getId();
                group = deletedContact.getGroups().iterator().next();
                break;
            }
        }
        //Если ни в одной группе нет контактов, записываем любой контакт в любую группу:
        if(deletedContact == null){
            deletedContact = app.db().contacts().iterator().next();
            app.goTo().homePage();
            app.contact().selectContactById(deletedContact.getId());
            group = app.db().groups().iterator().next();
            app.contact().addToGroup(group);
            id = deletedContact.getId();
        }
        //Берем список групп у контакта до удаления:
        Groups groupBefore = deletedContact.getGroups();
        // Удаляем из группы в ГУИ:
        app.goTo().homePage();
        app.contact().chooseGroup(group);
        app.contact().deleteFromGroup(deletedContact);
        //Ищем удаленный контакт по ID и берем его группы:
        contacts = app.db().contacts();
        for (ContactData contact : contacts) {
            if (contact.getId() == id) {
                deletedContact = contact;
                groupAfter = deletedContact.getGroups();
                break;
            }
        }
        //Проверяем группы, в которые входит контакт:
       assertThat(groupAfter.withAdded(group), equalTo(groupBefore));
    }
}



