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
public class ContactDataTest extends TestBase {

  @Test
  public void testContactData() {
    app.goTo().home();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    ContactData contactInfoFromVievForm = app.contact().infoFromViewForm(contact);
    assertThat(contact.getAllEmails(), equalTo(mergeEmail(contactInfoFromEditForm)));
    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    assertThat(contactInfoFromVievForm.getAllInfo(), equalTo(mergeAllInfo(contactInfoFromEditForm)));
  }

  private String mergeEmail(ContactData contact) {
    return Arrays.asList(contact.getEmail1(), contact.getEmail2(), contact.getEmail3())            //Формируем коллекцию email
            .stream().filter((s) -> ! s.equals(""))                                                //выбрасываем из нее пустые,
            .map(ContactDataTest::cleaned)                                                        //в каждому применяем функцию cleaned
            .collect(Collectors.joining("\n"));                                                    //собираем в одну строку
  }
  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone()) //Формируем коллекцию телефонов
            .stream().filter((s) -> ! s.equals(""))                                                //выбрасываем из нее пустые,
            .map(ContactDataTest::cleaned)                                                        //в каждому применяем функцию cleaned
            .collect(Collectors.joining("\n"));                                                    //собираем в одну строку
  }

  private String mergeAllInfo(ContactData contact) {
    String allPhones = Arrays.asList("H: " + contact.getHomePhone(), "M: " + contact.getMobilePhone(), "W: " + contact.getWorkPhone()).stream()        //делаем коллекцию телефонов
            .filter((s) -> ! s.equals("")).filter((s) -> ! s.equals("M: ")).filter((s) -> ! s.equals("W: ")).filter((s) -> ! s.equals("H: "))          //выкидываем пустые
            .collect(Collectors.joining("\n"));
    String allEmails = Arrays.asList(contact.getEmail1(), contact.getEmail2(), contact.getEmail3()).stream()                                           //делаем коллекцию email
            .filter((s) -> ! s.equals("")).collect(Collectors.joining("\n"));                                                                          //выкидываем пустые


    return Arrays.asList(contact.getFirstName() + " " + contact.getLastName(), contact.getAddress(),                                                   //собираем все данные
            "\n" + allPhones,"\n" + allEmails)
            .stream().filter((s) -> ! s.equals("")).filter((s) -> ! s.equals("\n")).collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

}
