package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroup() {
    List<Object[]> list = new ArrayList<Object[]>();             //Заполняем список массивох тестовых данных
    list.add(new Object[] {new GroupData().withName("Test1").withHeader("header 1").withFooter("footer 1")});
    list.add(new Object[] {new GroupData().withName("Test2").withHeader("header 2").withFooter("footer 3")});
    list.add(new Object[] {new GroupData().withName("Test3").withHeader("header 3").withFooter("footer 3")});
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
