package ru.stqa.pft.addressbook.model;

/**
 * Created by razgonyaev on 01.12.2016.
 */
public class ParamsObject {

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
}
