package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;


/**
 * Created by razgonyaev on 18.01.2017.
 */
public class AddInGroupTest extends TestBase {

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
    ContactData contact = app.db().contacts().stream().iterator().next();
    GroupData group = app.db().groups().stream().iterator().next();

    if(app.contact().verifyContactInGroup(contact, group)) {                            //Если контакт уже в выбранной группе, то создаем новую и добавляем в нее
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("For Test"));
      group = app.db().groups().stream()
              .max((g1, g2) -> Integer.compare(g1.getId(), g2.getId())).get();
    }

    app.goTo().home();
    app.contact().addInGroup(contact.getId(),group.getName());

    Assert.assertTrue(app.contact().verifyContactInGroup(app.db().contactWithId(contact.getId()), group));

  }


}
