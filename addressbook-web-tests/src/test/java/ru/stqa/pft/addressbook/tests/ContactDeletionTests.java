package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by gren on 04.12.2016.
 */
public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().home();
    if (!app.contact().isThereAContact()) {
      app.contact().create(new ContactData().withFirstName("Test1").withLastName("Test1").withAddress("address").withNickName("Test1").withMobilePhone("+7987654321"));
    }
  }

  @Test
  public void testContactDeletion() {
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().home();
    Assert.assertEquals(app.contact().count(), before.size() - 1);
    Contacts after = app.contact().all();

    assertThat(after, equalTo(before.without(deletedContact)));
  }

}
