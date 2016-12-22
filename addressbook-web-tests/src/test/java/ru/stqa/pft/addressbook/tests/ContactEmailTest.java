package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by razgonyaev on 22.12.2016.
 */
public class ContactEmailTest extends TestBase {

  @Test
  public void testContactPhone () {
    app.goTo().home();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllEmails(), equalTo(mergeEmail(contactInfoFromEditForm)));
  }

  private String mergeEmail(ContactData contact) {
    return Arrays.asList(contact.getEmail1(), contact.getEmail2(), contact.getEmail3())            //Формируем коллекцию email
            .stream().filter((s) -> ! s.equals(""))                                                //выбрасываем из нее пустые,
            .map(ContactEmailTest::cleaned)                                                        //в каждому применяем функцию cleaned
            .collect(Collectors.joining("\n"));                                                    //собираем в одну строку
  }


  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

}
