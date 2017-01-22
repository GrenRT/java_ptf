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

  @Column(name = "id")
  private int contactId;

  @Column(name = "group_id")
  private int groupId;

  public ContactInGroup(Collection<ContactInGroup> result) {
     new HashSet<ContactInGroup>(result);
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

