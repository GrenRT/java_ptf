package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by gren on 29.01.2017.
 */
public class AdminHelper extends HelperBase {

  public AdminHelper(ApplicationManager app) {
    super(app);
  }

  public void changeMailUser(String user) {
    login();
    app.goTo().manageUsers();
    selectUserByLogin(user);
    click(By.cssSelector("input[value='Reset Password']"));
  }

  private void selectUserByLogin(String user) {
    click(By.linkText(user));
  }

  private void login() {
  wd.get(app.getProperties("web.baseUrl") + "/login_page.php");
    type(By.name("username"), app.getProperties("web.adminLogin"));
    type(By.name("password"), app.getProperties("web.adminPassword"));
    click(By.cssSelector("input[value='Login']"));
  }
}
