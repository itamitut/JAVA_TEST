package ru.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import javax.xml.xpath.XPath;
import java.io.File;

/**
 * Created by Сергей on 11.04.2018.
 */
public class HelperBase {
    public WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    protected void click(By locator) {
        wd.findElement( locator ).click();
    }

    protected void type(By locator, String text) {
        click( locator );
        if (text != null) {
            String existingText = wd.findElement( locator ).getAttribute( "value" );
            if (!text.equals( existingText )) {
                wd.findElement( locator ).clear();
                wd.findElement( locator ).sendKeys( text );
            }
        }
    }
    protected void attach(By locator, String path) {
        File file = new File(path);
        if (file != null) {
                wd.findElement( locator ).sendKeys( file.getAbsolutePath());
            }
        }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public boolean isElementPresent(By locator) {
        try {
            wd.findElement( locator );
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
