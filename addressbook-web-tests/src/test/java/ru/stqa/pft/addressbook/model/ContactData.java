package ru.stqa.pft.addressbook.model;

/**
 * Created by razgonyaev on 01.12.2016.
 */
public class ContactData {

  private int id;
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

  public ContactData (String firstName, String middleName, String lastName, String nickName, String title, String companyName, String address, String homePhone, String mobilePhone, String email1, String email2, String group) {
    this.id = Integer.MAX_VALUE;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.nickName = nickName;
    this.title = title;
    this.companyName = companyName;
    this.address = address;
    this.homePhone = homePhone;
    this.mobilePhone = mobilePhone;
    this.email1 = email1;
    this.email2 = email2;
    this.group = group;
  }

  public ContactData(int id, String firstName, String lastName) {
    this.id = id;
    this.firstName = firstName;
    this.middleName = null;
    this.lastName = lastName;
    this.nickName = null;
    this.title = null;
    this.companyName = null;
    this.address = null;
    this.homePhone = null;
    this.mobilePhone = null;
    this.email1 = null;
    this.email2 = null;
    this.group = null;
  }


  public String getEmail2() {
    return email2;
  }

  public String getEmail1() {
    return email1;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getAddress() {
    return address;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getCompanyName() {
    return companyName;
  }

  public String getTitle() {
    return title;
  }

  public String getLastName() {
    return lastName;
  }

  public String getNickName() {
    return nickName;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public String getGroup() {
    return group;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
    return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
  }

  @Override
  public int hashCode() {
    int result = firstName != null ? firstName.hashCode() : 0;
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    return result;
  }

  public int getId() {
    return id;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }

}
