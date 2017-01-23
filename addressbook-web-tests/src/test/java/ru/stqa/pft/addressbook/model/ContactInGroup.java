package ru.stqa.pft.addressbook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Created by razgonyaev on 18.01.2017.
 */
@Entity
@Table(name = "address_in_groups")
public class ContactInGroup {

  @Id
  @Column(name = "domain_id")
  private int domainId;

  public int getDomainId() {
    return domainId;
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
            "domainId=" + domainId +
            ", contactId=" + contactId +
            ", groupId=" + groupId +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactInGroup that = (ContactInGroup) o;

    if (domainId != that.domainId) return false;
    if (contactId != that.contactId) return false;
    return groupId == that.groupId;
  }

  @Override
  public int hashCode() {
    int result = domainId;
    result = 31 * result + contactId;
    result = 31 * result + groupId;
    return result;
  }
}

