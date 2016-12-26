package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroup() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();             //Заполняем список массивох тестовых данных
    BufferedReader reader = new BufferedReader( new FileReader(new File("src/test/resources/groups.csv"))); //Создаем объект, из которого можно читать строки
    String line = reader.readLine(); //читаем первую строку
    while (line != null) {  //цикл выполняется пока есть строки
      String[] split = line.split(";"); //строки режутся на тестовые данные
      list.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])}); //заполнение тестовыми данными объекта GroupData
      line = reader.readLine(); //читается новая строка
    }
    return list.iterator();
  }

  @Test(dataProvider = "validGroup")
  public void testGroupCreation(GroupData group) {
    app.goTo().groupPage();
    Groups before = app.group().all();
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size() + 1));
    Groups after = app.group().all();

    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt())))); //Выбор максимального идентификатора
  }

  @Test(enabled = false)
  public void testBadGroupCreation() {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("test2'");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().all();

    assertThat(after, equalTo(before));
  }

}
