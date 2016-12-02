package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.appmanager.HelperBase;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Random;

/**
 * Created by razgonyaev on 02.12.2016.
 */
public class ContactHelper extends HelperBase {

  FirefoxDriver wd;
  static Random r = new Random();
  static int add = r.nextInt(10);

  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }

  protected void submitContactCreation() {
    click(By.name("submit"));
  }

  protected void fillContactForm(ContactData contactData) {
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
  }

  protected void gotoContactCreationForm() {
    click(By.linkText("add new"));
  }

  protected static ContactData getParamsObject() {
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
    return contactData;
  }
}
