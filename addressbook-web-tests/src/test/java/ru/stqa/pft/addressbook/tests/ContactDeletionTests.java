package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by gren on 04.12.2016.
 */
public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().gotoHome();
    if (!  app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData(0,"Test1", "Test1"));
    }
        app.getContactHelper().openModificationForm();
    app.getContactHelper().deleteContact();
  }
}
