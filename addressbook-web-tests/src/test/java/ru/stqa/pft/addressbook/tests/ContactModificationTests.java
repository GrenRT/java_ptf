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
    if (!  app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData(0,"Test1", "Test1"));
    }
    ContactData contact = new ContactData(0,"Test1", "Test1");
    app.getContactHelper().openModificationForm();
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
  }
}
