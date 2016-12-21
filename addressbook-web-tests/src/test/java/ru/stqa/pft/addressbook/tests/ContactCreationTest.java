package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().home();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstName("Test1").withLastName("Test1").withAddress("address").withNickName("Test1").withMobilePhone("+7987654321");
    app.contact().create(contact);
    app.goTo().home();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Assert.assertEquals(before, after);
  }
}
