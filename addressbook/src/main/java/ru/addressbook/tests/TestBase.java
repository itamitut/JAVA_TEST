package ru.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.addressbook.appmanager.ApplicationManager;

/**
 * Created by Сергей on 09.04.2018.
 */
public class TestBase {

     ApplicationManager app = new ApplicationManager( BrowserType.FIREFOX);

    @BeforeMethod
    public void setUp() throws Exception {
        app.init();
    }

    @AfterMethod
    public void tearDown() {

        app.stop();
    }

}
