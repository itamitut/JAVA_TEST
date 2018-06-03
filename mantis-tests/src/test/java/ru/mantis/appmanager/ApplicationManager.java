package ru.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by Сергей on 11.04.2018.
 */
public class ApplicationManager {

    private final Properties properties;
    public WebDriver wd;
    private String browser;


    public ApplicationManager(String browser) {

        this.browser = browser;
        properties = new Properties();
            }

    public void init()  throws IOException{
        String target = System.getProperty("target","local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        if(Objects.equals( browser, BrowserType.FIREFOX )){
            wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true).setBinary( "C:/Program Files (x86)/Mozilla FirefoxESR/firefox.exe" ) );
        } else if(Objects.equals( browser, BrowserType.CHROME )){
            wd = new ChromeDriver();
        } else if (Objects.equals( browser, BrowserType.IE )){
            wd = new InternetExplorerDriver();
        }
        wd.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.baseUrl"));

    }
    public void stop(){
        wd.quit();
    }

}
