package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by gren on 04.12.2016.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.goTo().gotoHome();
    if (!  app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Test1", "Test1","Test1","Test1","Test1","Test1","Test1","8800235","+7987654321","Test1@test.com","Test2@etst.com","test2"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().openModificationForm(before.size() + 1);
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Test1", "Test1");
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.goTo().gotoHome();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }
}
