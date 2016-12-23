package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by razgonyaev on 23.12.2016.
 */
public class ContactViewTest extends TestBase {

  @Test

  public void testContactViev() {
    app.goTo().home();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    ContactData contactInfoFromVievForm = app.contact().infoFromViewForm(contact);
    assertThat(contactInfoFromVievForm.getAllInfo(), equalTo(mergeAllInfo(contactInfoFromEditForm)));

  }

  private String mergeAllInfo(ContactData contact) {
    String allPhones = Arrays.asList("H: " + contact.getHomePhone(), "M: " + contact.getMobilePhone(), "W: " + contact.getWorkPhone()).stream()
            .filter((s) -> ! s.equals("")).filter((s) -> ! s.equals("M: ")).filter((s) -> ! s.equals("W: ")).filter((s) -> ! s.equals("H: "))
            .collect(Collectors.joining("\n"));
    String allEmails = Arrays.asList(contact.getEmail1(), contact.getEmail2(), contact.getEmail3()).stream().filter((s) -> ! s.equals("")).collect(Collectors.joining("\n"));


    return Arrays.asList(contact.getFirstName() + " " + contact.getLastName(), contact.getAddress(),
            "\n" + allPhones,"\n" + allEmails)
            .stream().filter((s) -> ! s.equals("")).filter((s) -> ! s.equals("\n")).collect(Collectors.joining("\n"));
  }

}
