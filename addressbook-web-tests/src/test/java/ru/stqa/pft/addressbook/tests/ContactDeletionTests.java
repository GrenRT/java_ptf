package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
/**
 * Created by gren on 04.12.2016.
 */
public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    app.contactHelper().gotoHome();
    app.contactHelper().openModificationForm();
    app.contactHelper().deleteContact();
  }
}
