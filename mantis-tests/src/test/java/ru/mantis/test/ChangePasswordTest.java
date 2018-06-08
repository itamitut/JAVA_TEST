package ru.mantis.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.mantis.model.MailMessage;
import ru.mantis.model.Users;
import ru.mantis.model.UsersData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTest extends TestBase{

    public String username ;
    private String email;
    String newPassword = "1234";

    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }
    @Test
    public void changePasswordTest() throws IOException, MessagingException {

    //  Выберем из БД имя юзера, которому будем менять пароль:
        Users users = DBTestMantis.users();
        for (UsersData user : users){
          if(!user.getName().equals("administrator")) {
              username = user.getName();
              email = user.getEmail();
          break;
        }
    }
        app.init();
        app.registration().loginAsAdmin();
        app.registration().manageUser(username);

        //Получаем письмо с ссылкой:
        List<MailMessage> mailMessages = app.mail().waitForMail(1,60000 );
        String confirmationLink = findConfirmationLink( mailMessages, email);
        app.registration().finish(confirmationLink, newPassword );
        assertTrue(app.newSession().login(username, newPassword ));
        app.stop();
    }
    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore()
                .build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }


}
