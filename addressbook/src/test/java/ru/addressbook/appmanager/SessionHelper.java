package ru.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Сергей on 11.04.2018.
 */
public class SessionHelper extends HelperBase {

    public void login(String username, String password) {
        type(By.name("user"), username);
        type(By.name("pass"), password);
        click(By.xpath("//form[@id='LoginForm']/input[3]"));
    }
    public SessionHelper(WebDriver wd) {
        super(wd);
    }
}
