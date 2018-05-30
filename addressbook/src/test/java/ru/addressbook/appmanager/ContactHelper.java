package ru.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;

import java.io.File;
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
//        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
 //       type(By.name("email"), contactData.getEmail());
  //      attach(By.name("photo"), contactData.getPhoto().getPath());
 //       type(By.name("notes"), contactData.getNotes());
        if(creation){
            new Select( wd.findElement( By.name("new_group"))).selectByVisibleText("test 1");
            contactData.getGroup();
        } else {
            Assert.assertFalse( isElementPresent( By.name("new_group")));
        }
}

    public void submitNewContact()  {
        wd.findElements(By.cssSelector("input[name=submit]")).get(1).click();
    }
    public void updateContact() {
        click(By.name("update"));
    }

    public void create(ContactData contact)  {
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
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String lastname  = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String address   = cells.get(3).getText();
            String allEmails = cells.get(4).getText();
            String allPhones = cells.get(5).getText();
 //Режем строку по символу новой строки
//            String[] phones = cells.get(5).getText().split("\n");
            ContactData contact = new ContactData().withId(Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value")))
                    .withFirstname(firstname).withLastname(lastname).withAddress(address)
                    .withAllPhones(allPhones).withAllEmails(allEmails);
            contacts.add(contact);
        }
        return contacts;
    }


    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        wd.findElement(By.cssSelector("input[value=Delete]")).click();
    }

    public void selectContactById(int id) {
        wd.findElement( By.cssSelector(String.format("input[value='%s']",id))).click();
    }
    //Находим строку модифицируемого контакта по id и кликаем Edit:
    public void initContactModificationById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",id))).click();
    }

    public void modify(ContactData modifiedContact, ContactData contact) {
        initContactModificationById(modifiedContact.getId());
        fillNewContact(contact,false);
        updateContact();

    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withPhoneHome(home).withMobilePhone(mobile).withWorkPhone(work)
                .withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3);
    }
 /*   private void initContactModificationById(int id){
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id )));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();

    }*/
}


