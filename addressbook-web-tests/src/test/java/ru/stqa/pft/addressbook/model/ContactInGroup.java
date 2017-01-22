package ru.stqa.pft.addressbook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Collection;
import java.util.List;

/**
 * Created by razgonyaev on 18.01.2017.
 */
@Entity
@Table(name = "address_in_groups")

public class ContactInGroup {

  @Column(name = "id")
  private int contactId;
  @Column(name = "group_id")
  private int groupId;

  public ContactInGroup(Collection<ContactInGroup> result) {
    setContactId(3);
  }

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

}

