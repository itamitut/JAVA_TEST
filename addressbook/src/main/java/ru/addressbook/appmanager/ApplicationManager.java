package ru.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

/**
 * Created by Сергей on 11.04.2018.
 */
public class ApplicationManager {
    public FirefoxDriver wd;
    private NavigationHelper navigationHelper;
    private  GroupHelper groupHelper ;
    private SessionHelper sessionHelper;
    private ContactHelper contactHelper;


    public ApplicationManager() {

    }


    public void init() {
        wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true)
        .setBinary("C:/Program Files (x86)/Mozilla FirefoxESR/firefox.exe"));
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/");
        groupHelper = new GroupHelper(wd);
        contactHelper = new ContactHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login("Admin", "secret");
    }

    public void stop() {
        contactHelper.wd.quit();
    }

    public void goToAddContact() {
        contactHelper.wd.findElement(By.linkText("add new")).click();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public void goToGroupPage() {navigationHelper.goToGroupPage();}

    public void goToHomePage() {navigationHelper.goToHomePage();}

    public ContactHelper getContactHelper() {
        return contactHelper;
    }


}
