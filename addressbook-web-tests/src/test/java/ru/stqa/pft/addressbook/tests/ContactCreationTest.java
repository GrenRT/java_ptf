package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactFromXml() throws IOException {
    try (BufferedReader reader = new BufferedReader( new FileReader(new File("src/test/resources/contacts.xml")))) {  //Создаем объект, из которого можно читать строки
      String xml = "";
      String line = reader.readLine(); //читаем первую строку
      while (line != null) {  //цикл выполняется пока есть строки
        xml += line;
        line = reader.readLine(); //читается новая строка
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(ContactData.class);
      List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
      return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
    }
  }
  @DataProvider
  public Iterator<Object[]> validContactFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader( new FileReader(new File("src/test/resources/contacts.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());   //здесь делается магия
      return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();  //каждый объект заворачиваем в массив, состояжий из этого объекта, потому что так хочет фреймворк TestNG
    }
  }

  @Test(dataProvider = "validContactFromJson")
  public void testContactCreation(ContactData contact) {
    Contacts before = app.db().contacts();
    Groups groups = app.db().groups();
    File photo = new File("src/test/resources/peng.jpg");
    app.goTo().home();
    app.contact().create(contact.withPhoto(photo).inGroup(groups.iterator().next()));
    app.goTo().home();
    Assert.assertEquals(app.contact().count(), before.size() + 1);
    Contacts after = app.db().contacts();

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}
