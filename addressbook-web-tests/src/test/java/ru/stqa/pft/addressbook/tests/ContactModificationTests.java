package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.ContactHelper;

/**
 * Created by gren on 04.12.2016.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.contactHelper().gotoHome();
    app.contactHelper().openModificationForm();
    app.contactHelper().fillContactForm(ContactHelper.getParamsObject(), false);
    app.contactHelper().submitContactModification();
  }
}
