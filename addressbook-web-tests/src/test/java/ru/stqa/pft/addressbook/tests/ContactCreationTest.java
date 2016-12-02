package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class ContactCreationTest extends TestBase {


  @Test
  public void testContactCreation() {
    app.contactHelper().gotoContactCreationForm();
    app.contactHelper().fillContactForm(ContactHelper.getParamsObject());
    app.contactHelper().submitContactCreation();
  }
}
