package ru.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by Сергей on 11.04.2018.
 */
public class ApplicationManager {
    public WebDriver wd;
    private NavigationHelper navigationHelper;
    private  GroupHelper groupHelper ;
    private SessionHelper sessionHelper;
    private ContactHelper contactHelper;
    private String browser;


    public ApplicationManager(String browser) {
        this.browser = browser;
    }


    public void init() {

        if(Objects.equals( browser, BrowserType.FIREFOX )){
            wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true).setBinary( "C:/Program Files (x86)/Mozilla FirefoxESR/firefox.exe" ) );
        } else if(Objects.equals( browser, BrowserType.CHROME )){
            wd = new ChromeDriver();
        } else if (Objects.equals( browser, BrowserType.IE )){
            wd = new InternetExplorerDriver();
        }

        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/group.php");
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
