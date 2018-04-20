package ru.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Сергей on 11.04.2018.
 */
public class NavigationHelper extends BaseHelper {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }
    public void goToGroupPage() {
        click(By.linkText("groups"));
    }
    public void goToHomePage() {
        click(By.linkText("home"));
    }
    public void closeAlert(){
    wd.switchTo().alert().accept();}
}
