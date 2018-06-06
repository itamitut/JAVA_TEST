package ru.mantis.appmanager;

import com.sun.org.apache.xml.internal.security.utils.HelperNodeList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.mantis.test.TestBase;

public class RegistrationHelper extends HelperBase {

    public RegistrationHelper(ApplicationManager app) {
        super(app);

    }

    public void start(String username, String email) {
        wd.get( app.getProperty("web.baseUrl") + "signup_page.php");
        type( By.name("username"),username);
        type( By.name("email"),email);
        click(By.cssSelector("input[value='Signup']"));

    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("input[value='Update User']"));
    }

    public void loginAsAdmin() {
        wd.get( app.getProperty("web.baseUrl"));
        type( By.name("username"),app.getProperty("web.adminLogin"));
        type( By.name("password"),app.getProperty("web.adminPassword"));
        click(By.cssSelector("input[value='Login']"));
    }

    public void manageUser() {
        wd.findElement(By.cssSelector("a[href='/mantisbt-1.2.20/manage_user_page.php']")).click();
     //Выбираем юзера:
        //   wd.findElement(By.linkText("User1")).click();
    }
}
