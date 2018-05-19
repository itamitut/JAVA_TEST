package ru.addressbook.tests;

import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by Сергей on 10.05.2018.
 */
public class ContactPhonesTest extends TestBase {
    @Test
    public void testContactPhones() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat( contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat( contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
        assertThat( contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
          }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3())
                .stream().filter((s) -> ! s.equals("")).collect( Collectors.joining("\n"));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone())
        .stream().filter((s) -> ! s.equals(""))
                .map(ContactPhonesTest::cleaned)
                .collect( Collectors.joining("\n"));
    }
// Удаление из строки ненужных символов
    public static String cleaned(String phone) {
        return phone.replaceAll( "\\s", "" ).replaceAll( "[-()]", "" );
    }
}

