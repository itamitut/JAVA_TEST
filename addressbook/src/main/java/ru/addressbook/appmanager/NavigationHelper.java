package ru.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Сергей on 11.04.2018.
 */
public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }
    public void groupPage() {
        click(By.linkText("groups"));
    }
    public void homePage() { click(By.linkText("home"));
    }
    public void closeAlert(){
    wd.switchTo().alert().accept();}
}
