package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by razgonyaev on 02.12.2016.
 */
public class ContactHelper extends HelperBase {

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
    attach(By.name("photo"), contactData.getPhoto());

    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void gotoContactCreationForm() {
    click(By.linkText("add new"));
  }

  //Открывает модификацию контакт в списке
  public void openModifyForm(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s'", id))).click();
  }

  //Отктывает окно просмотра информации о контакте
  private void openViewForm(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='view.php?id=%s'", id))).click();
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
      String lastName = cells.get(1).getText();
      String firstName = cells.get(2).getText();
      String allPhones = cells.get(5).getText();
      String allMails = cells.get(4).getText();
      String address = cells.get(3).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData().withId(id).withFirstName(firstName).withLastName(lastName).withAllPhones(allPhones).withAllEmails(allMails).withAddress(address);
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
    String email1 = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getText();
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstName).withLastName(lastName).withAddress(address)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
            .withEmail1(email1).withEmail2(email2).withEmail3(email3);
  }

  public ContactData infoFromViewForm(ContactData contact) {
    openViewForm(contact.getId());
    String allInfo = wd.findElement(By.cssSelector("div#content")).getText();
    wd.navigate().back();
    return new ContactData().withAllInfo(allInfo);
  }

  public void addInGroup(int id, String group) {
    selectContactById(id);
    selectGroupForAdd(group);
    click(By.name("add"));
  }

  private void selectGroupForAdd(String group) {
    new Select(wd.findElement(By.cssSelector("div.right > select"))).selectByVisibleText(group);
  }
  public boolean verifyContactInGroup(ContactData contact, GroupData group) {
    boolean sing = false;
    for(GroupData g : contact.getGroups()) {
      if(group.getId() == g.getId()) {
        sing =  true;
        break;
      } else {
        sing = false;
      }
    }
    return sing;
  }

  public void removeOfGroup(int contactId, int groupId) {
    selectGroupForDisplayById(groupId);
    selectContactById(contactId);
    click(By.name("remove"));
  }

  private void selectGroupForDisplayById(int id) {
    new Select(wd.findElement(By.cssSelector("#right > select:nth-child(1)"))).selectByValue(String.format("%s", id));
  }
}
