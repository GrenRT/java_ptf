package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;


/**
 * Created by gren on 29.01.2017.
 */
public class ChangeMailTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testChangeMail() throws IOException {
    String user = "user1485635023776";
    String password = "test123";
    String email = "user1485635023776@localhost.localdomain";

    app.adminHelper().changeMailUser(user);
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String changeMailLink = findChangeMailLink(mailMessages, email);
    app.goTo().link(changeMailLink);
    app.userHelper().changePassword(changeMailLink, password);

    assertTrue(app.newSession().login(user, password));
  }

  private String findChangeMailLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }

}
