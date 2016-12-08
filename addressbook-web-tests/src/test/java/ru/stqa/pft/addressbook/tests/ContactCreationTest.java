package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() {
    app.contactHelper().gotoContactCreationForm();
    app.contactHelper().fillContactForm(ContactData.getParamsObject(), true);
    app.contactHelper().submitContactCreation();
  }
}
