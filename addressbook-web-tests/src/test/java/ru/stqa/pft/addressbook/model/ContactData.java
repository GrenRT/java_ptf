package ru.stqa.pft.addressbook.model;

import java.util.Random;

/**
 * Created by razgonyaev on 01.12.2016.
 */
public class ContactData {

  static Random r = new Random();
  static int add = r.nextInt(10);

  private String firstName;
  private String middleName;
  private String lastName;
  private String nickName;
  private String title;
  private String companyName;
  private String address;
  private String homePhone;
  private String mobilePhone;
  private String email1;
  private String email2;
  private String group;

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

  public String getEmail2() {
    return email2;
  }

  public void setEmail2(String email2) {
    this.email2 = email2;
  }

  public String getEmail1() {
    return email1;
  }

  public void setEmail1(String email1) {
    this.email1 = email1;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public void setMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public void setHomePhone(String homePhone) {
    this.homePhone = homePhone;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }
}
