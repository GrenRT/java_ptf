package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.ContactHelper;

public class ContactCreationTest extends TestBase {


  @Test
  public void testContactCreation() {
    app.contactHelper().gotoContactCreationForm();
    app.contactHelper().fillContactForm(ContactHelper.getParamsObject());
    app.contactHelper().submitContactCreation();
  }
}
