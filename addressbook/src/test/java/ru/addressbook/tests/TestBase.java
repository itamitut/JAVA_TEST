package ru.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.addressbook.appmanager.ApplicationManager;
import ru.addressbook.model.GroupData;
import ru.addressbook.model.Groups;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Сергей on 09.04.2018.
 */
public class TestBase {

     protected  static final ApplicationManager app
             = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }
    public void verifyGroupListInUI() {
        //Проверяем в параметрах запуска: -DverifyUI=true
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();
            assertThat( uiGroups, equalTo( dbGroups.stream()
                    .map( (g) -> new GroupData().withId( g.getId() ).withName( g.getName() ) )
                    .collect( Collectors.toSet() ) ) );
        }
    }
}
