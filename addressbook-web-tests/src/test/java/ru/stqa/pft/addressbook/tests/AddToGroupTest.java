package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by razgonyaev on 18.01.2017.
 */
public class AddToGroupTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditionsContacts() {
    app.goTo().home();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstName("Test1").withLastName("Test1").withAddress("address").withNickName("Test1").withMobilePhone("+7987654321"));
    }
  }
  @BeforeMethod
  public void ensurePreconditionsGroups() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Test1"));
    }
  }

  @Test
  public void testAddToGroup() {
    Contacts beforeContacts = app.db().contacts();
    Groups beforeGroups = app.db().groups();
    ContactData contact = beforeContacts.stream().iterator().next();
    GroupData group = beforeGroups.stream().iterator().next();

    app.goTo().home();
    app.contact().addToGroup(contact.getId(),group.getName());
    ContactInGroup afterAdd = app.db().contactInGroup();

    assertThat(contact.getId(), equalTo(afterAdd.getContactId()));
    assertThat(group.getId(), equalTo(afterAdd.getGroupId()));
  }

}
