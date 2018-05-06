package ru.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;
import ru.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Сергей on 12.04.2018.
 */
public class ContactHelper extends HelperBase {


    public ContactHelper(WebDriver wd) {
        super(wd);
    }


    public void initContactCreation() {
        click(By.xpath("//div[@id='nav']//a[.='add new']"));}

    public void fillNewContact(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
//        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
//        type(By.name("nickname"), contactData.getNickname());
//        type(By.name("title"), contactData.getTitle());
//        type(By.name("company"), contactData.getCompany())
//       type(By.name("address"), contactData.getAddress());
//       type(By.name("home"), contactData.getHome());
//        type(By.name("mobile"), contactData.getMobile());
//        type(By.name("email"), contactData.getEmail());
 //       type(By.name("notes"), contactData.getNotes());
        if(creation){
            new Select( wd.findElement( By.name("new_group"))).selectByVisibleText("test1");
            contactData.getGroup();
        } else {
            Assert.assertFalse( isElementPresent( By.name("new_group")));
        }
}

    public void submitNewContact() {
        List<WebElement> submits = wd.findElements(By.cssSelector("input[name=submit]"));
        submits.get(1).click();
    }
    public void updateContact() {
        click(By.name("update"));
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillNewContact(contact, true);
        submitNewContact();
    }
    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> rows = wd.findElements(By.cssSelector("tr[name=entry]"));

        for(WebElement row : rows){
            ContactData contact = new ContactData().withFirstname(row.findElement(By.xpath("td[3]")).getText())
                    .withLastname(row.findElement(By.xpath("td[2]")).getText());
            contacts.add(contact);
        }
        return contacts;
    }
    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> rows = wd.findElements( By.cssSelector( "tr[name=entry]" ) );

        for (WebElement row : rows) {
            ContactData contact = new ContactData().withId(Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value")))
                    .withFirstname(row.findElement(By.xpath("td[3]")).getText())
                    .withLastname(row.findElement(By.xpath("td[2]")).getText());
            contacts.add( contact );
        }
        return contacts;
    }
    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        wd.findElement(By.cssSelector("input[value=Delete]")).click();
    }

    public void selectContactById(int id) {
        wd.findElement( By.cssSelector("input[value='"+id+"']")).click();
    }
    //Находим строку модифицируемого контакта по id и кликаем Edit:
    public void initModification(ContactData contact) {
        wd.findElement(By.cssSelector("a[href='edit.php?id="+contact.getId()+"']")).click();
    }
    public void modify(ContactData modifiedContact, ContactData contact) {
        initModification(modifiedContact);
        fillNewContact(contact,false);
        updateContact();

    }
}


