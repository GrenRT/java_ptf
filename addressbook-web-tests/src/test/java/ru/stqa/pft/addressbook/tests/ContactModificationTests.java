package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by gren on 04.12.2016.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHome();
    if (!  app.contactHelper().isThereAContact()) {
      app.contactHelper().createContact();
    }
    app.contactHelper().openModificationForm();
    app.contactHelper().fillContactForm(ContactData.getParamsObject(), false);
    app.contactHelper().submitContactModification();
  }
}
