package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
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

  public void fillContactForm(ContactData contactData, boolean creation) {
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
      Select select = new Select(wd.findElement(By.name("new_group")));
      List<WebElement> elements = select.getOptions();
      for (WebElement webElement : elements) {
        if (webElement.getText() != null && webElement.getText().equals(contactData.getGroup())) {
          select.selectByVisibleText(contactData.getGroup());
          break;
        }
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void gotoContactCreationForm() {
    click(By.linkText("add new"));
  }

  public void openModificationForm() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void deleteContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("Edit"));
  }

  private void gotoHome() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("home"));
  }

  public void createContact(ContactData contact) {
    gotoContactCreationForm();
    fillContactForm(contact, true);
    submitContactCreation();
    gotoHome();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<>();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr"));
    elements.remove(0);

    for (WebElement element : elements) {
      String[] name = element.getText().split("\\s+");
      String firstName = name[0];
      String lastName = name[1];
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData(id, firstName, lastName);
      contacts.add(contact);
    }
    return contacts;
  }

  public void closeWindows() {
    wd.switchTo().alert().accept();
  }
}
