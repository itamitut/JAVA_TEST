package ru.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;

import javax.xml.xpath.XPath;

/**
 * Created by Сергей on 11.04.2018.
 */
public class BaseHelper {
    protected FirefoxDriver wd;

    public BaseHelper(FirefoxDriver wd) {
        this.wd =wd;
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        click(locator);
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
    }
    public  boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public void selectContact() {
        wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();}
}
