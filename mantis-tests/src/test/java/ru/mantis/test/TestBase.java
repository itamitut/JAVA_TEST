package ru.mantis.test;

import com.google.protobuf.ServiceException;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.mantis.appmanager.ApplicationManager;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

/**
 * Created by Сергей on 09.04.2018.
 */
public class TestBase {

     protected  static final ApplicationManager app
             = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    //    app.ftp().upload(new File("src/test/resources/config_inc.php"),
     //           "config_inc.php", "config_inc.php.bak");
    }

    @AfterSuite
    public void tearDown() throws IOException {
     //   app.ftp().restore("config_inc.php.bak","config_inc.php");
        app.stop();
    }
    public boolean isIssueOpen(int issueId) throws MalformedURLException,  RemoteException, javax.xml.rpc.ServiceException {
        String closed = "closed";
        String resolved = "resolved";
        String resolution = app.soap().getIssue(issueId).getStatus().getName();
        if (resolution!=closed||resolution!=resolved){
            return true;
        } else {return false;}
    }

    public void skipIfNotFixed(int issueId) throws MalformedURLException, ServiceException, RemoteException, javax.xml.rpc.ServiceException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

}
