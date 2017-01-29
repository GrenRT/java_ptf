package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by gren on 29.01.2017.
 */
public class UserHelper extends HelperBase {

  public UserHelper(ApplicationManager app) {
    super(app);
  }

  public void changePassword(String changeMailLink, String password) {
    wd.get(changeMailLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("input[value='Update User']"));
  }
}
