package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by razgonyaev on 01.12.2016.
 */
@XStreamAlias("contact") //задаем подсказку, как называть данные при сохранении в xml
@Entity
@Table(name = "addressbook")

public class ContactData {

  @XStreamOmitField  //не сохранять это поле в xml
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;;
  @Expose
  @Column(name = "firstname")
  private String firstName;

  private String middleName;

  @Expose
  @Column(name = "lastname")
  private String lastName;

  private String nickName;
  private String title;

  @Column(name = "company")
  private String companyName;

  @Expose
  @Column(name = "address")
  @Type(type = "text")
  private String address;

  @Column(name = "home")
  @Type(type = "text")
  private String homePhone;

  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobilePhone;

  @Column(name = "work")
  @Type(type = "text")
  private String workPhone;


  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private String email1;
  @Column(name = "email2")
  @Type(type = "text")
  private String email2;
  @Column(name = "email3")
  @Type(type = "text")
  private String email3;

  @Transient
  private String allPhones;

  @Transient
  private String allEmails;

  @Transient
  private String allInfo;

  @Column(name = "photo")
  @Type(type = "text")
  private String photo;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();

  public File getPhoto() {
    try {                                       //Ловим NPE
      return new File(photo);
    } catch (NullPointerException e) {
      return null;
    }
  }

  public String getAllPhones() {
    return allPhones;
  }

  public int getId() {
    return id;
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

  public String getWorkPhone() {
    return workPhone;
  }

  public String getEmail3() {
    return email3;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public String getAllInfo() {
    return allInfo;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactData withMiddleName(String middleName) {
    this.middleName = middleName;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData withNickName(String nickName) {
    this.nickName = nickName;
    return this;

  }

  public Groups getGroups() {
    return new Groups(groups);
  }

  public ContactData withTitle(String title) {
    this.title = title;
    return this;
  }

  public ContactData withCompanyName(String companyName) {
    this.companyName = companyName;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withEmail1(String email1) {
    this.email1 = email1;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withAllInfo(String allInfo) {
    this.allInfo = allInfo;
    return this;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
    return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;

  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    return result;
  }


  public ContactData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }
}
