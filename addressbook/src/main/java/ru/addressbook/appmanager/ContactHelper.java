package ru.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.addressbook.model.ContactData;

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
    public void deleteContact() {
        wd.findElement(By.xpath("//div[@id='content']/form[2]/div[2]/input")).click();
    }

    public void editContact() {
        wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img")).click();
    }
    public void submitNewContact() {
        wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }
    public void updateContact() {
        click(By.name("update"));
    }

    public void createContact() {
        initContactCreation();
        fillNewContact(new ContactData("Robert","Stepanovich","Zizi","yuric",
                "Mr","Cisco","Avenue","84955463217","8916549809","i@yandex.ru","friend of mine", "test1" ),true);
        submitNewContact();
    }
}
