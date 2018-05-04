package ru.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.addressbook.model.ContactData;
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
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHome());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("email"), contactData.getEmail());
        type(By.name("notes"), contactData.getNotes());
        if(creation){
            new Select( wd.findElement( By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse( isElementPresent( By.name("new_group")));
        }

}
    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }
    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }
    public void deleteContact(int index) {
        selectContact( index);
        wd.findElement(By.cssSelector("input[value=Delete]")).click();
    }

    public void editContact(int index) {
        List<WebElement> edits = wd.findElements(By.cssSelector("img[title=Edit]"));
        edits.get(index).click();
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
            ContactData contact = new ContactData(row.findElement(By.xpath("td[3]")).getText(), row.findElement(By.xpath("td[2]")).getText(), null);
            contacts.add(contact);
        }
        return contacts;

    }
}
