package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.appmanager.HelperBase;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Random;

/**
 * Created by razgonyaev on 02.12.2016.
 */
public class ContactHelper extends HelperBase {

  static Random r = new Random();
  static int add = r.nextInt(10);

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
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void gotoContactCreationForm() {
    click(By.linkText("add new"));
  }

  public static ContactData getParamsObject() {
    ContactData contactData = new ContactData();
    contactData.setNickName("Test" + add);
    contactData.setFirstName("Test" + add);
    contactData.setMiddleName("Test" + add);
    contactData.setLastName("Test" + add);
    contactData.setTitle("title");
    contactData.setCompanyName("company");
    contactData.setAddress("address");
    contactData.setHomePhone("" + (r.nextInt(100000) + 1000000));
    contactData.setMobilePhone("+7987" + r.nextInt(1000000));
    contactData.setEmail1("test@test.test");
    contactData.setEmail2("test2@test.test");
    contactData.setGroup("test2");
    return contactData;
  }

  public void openModificationForm() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void deleteContact() {
    click(By.xpath("//div[@id='content']/form[2]/input[2]"));
  }

}