package ru.mantis.test;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.mantis.appmanager.HttpSession;
import java.io.IOException;
import static org.testng.Assert.assertTrue;

public class ChangePasswordTest extends TestBase{

    String user = "user1";
    String password = "NewPassword";
    @Test
    public void changePasswordTest() throws IOException {

        app.init();
        app.db().users();
    //    app.registration().loginAsAdmin();
    //    app.registration().manageUser();
        app.stop();

    }
}
