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
public class ContactPhoneTest extends TestBase {

  @Test
  public void testContactPhone () {
    app.goTo().home();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone()) //Формируем коллекцию телефонов
            .stream().filter((s) -> ! s.equals(""))                                                //выбрасываем из нее пустые,
            .map(ContactPhoneTest::cleaned)                                                        //в каждому применяем функцию cleaned
            .collect(Collectors.joining("\n"));                                                    //собираем в одну строку
  }


  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}
