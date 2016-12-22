package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Created by razgonyaev on 02.12.2016.
 */
public class ContactHelper extends HelperBase {

  String stringNumber;

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void fill(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("middlename"), contactData.getMiddleName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("nickname"), contactData.getNickName());
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompanyName());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("email"), contactData.getEmail1());
    type(By.name("email2"), contactData.getEmail2());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText("[none]");// выбираем [none], т.к. при создании контакта не задаем ему группу.
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void gotoContactCreationForm() {
    click(By.linkText("add new"));
  }

  //Открывает контакт в списке
  public void openModifyForm(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s'", id))).click();
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public int count () {
    return  wd.findElements(By.name("entry")).size();
  }

  public void submit() {
    click(By.name("update"));
  }

  public void delete(ContactData contact) {
   selectContactById(contact.getId());
   deleteContact();
   closeWindows();
  }

  public void deleteContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }


  public boolean isThereAContact() {
    return isElementPresent(By.name("entry"));
  }

  private void gotoHome() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("home"));
  }

  public void create(ContactData contact) {
    gotoContactCreationForm();
    fill(contact, true);
    submitContactCreation();
    gotoHome();
  }

  public void modify(ContactData contact) {
    openModifyForm(contact.getId());
    fill(contact, false);
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String firstName = cells.get(1).getText();
      String lastName = cells.get(2).getText();
      String[] phones = cells.get(5).getText().split("\n");
      //Будет ошибка, если нет хотя бы одного телефона в контакте
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData().withId(id).withFirstName(firstName).withLastName(lastName).withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]);
      contacts.add(contact);
    }
    return contacts;
  }

  public void closeWindows() {
    wd.switchTo().alert().accept();
  }

  public ContactData infoFromEditForm(ContactData contact) {
    openModifyForm(contact.getId());
    String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstName).withLastName(lastName).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
  }
}
