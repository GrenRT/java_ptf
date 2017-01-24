package ru.stqa.pft.addressbook.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by razgonyaev on 18.01.2017.
 */
@Entity
@Table(name = "address_in_groups")
public class ContactInGroup {


  //private int domain_id;
  @Id
  @Column(name = "created")
  private LocalDateTime created;

  public LocalDateTime getCreatedTime() {
    return created;
  }

  @Column(name = "id")
  private int contactId;

  @Column(name = "group_id")
  private int groupId;

  public int getContactId() {
    return contactId;
  }

  public void setContactId(int contactId) {
    this.contactId = contactId;
  }

  public int getGroupId() {
    return groupId;
  }

  public void setGroupId(int groupId) {
    this.groupId = groupId;
  }

  @Override
  public String toString() {
    return "ContactInGroup{" +
            "domainId=" + created +
            ", contactId=" + contactId +
            ", groupId=" + groupId +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactInGroup that = (ContactInGroup) o;

    if (contactId != that.contactId) return false;
    if (groupId != that.groupId) return false;
    return created != null ? created.equals(that.created) : that.created == null;
  }

  @Override
  public int hashCode() {
    int result = created != null ? created.hashCode() : 0;
    result = 31 * result + contactId;
    result = 31 * result + groupId;
    return result;
  }
}

