package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().gotoHome();
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("Test1", "Test1","Test1","Test1","Test1","Test1","Test1","8800235","+7987654321","Test1@test.com","Test2@etst.com","test2");
    app.getContactHelper().createContact(contact);
    app.goTo().gotoHome();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
