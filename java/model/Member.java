package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Creating class Member.
 */
public class Member { 
  private String name;
  private String email;
  private String mophnumber;
  private String memberid;
  private int credits;
  private Date creationDate;
  private List<Item> ownedItems;
  private MemberIdGenerator fh = new MemberIdGenerator();


  /**
 * constructor.
 hejdå   */

  public Member(String name, String email, String mophnumber, String memberid) {
    this.name = name;
    this.email = email;
    this.mophnumber = mophnumber;
    this.memberid = fh.generateRandomId(6);
    this.creationDate = new Date();
    this.credits = 100;
    this.ownedItems = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name; 
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMoPhNumber() {
    return mophnumber;
  }

  public void setMoPhNumber(String mophnumber) {
    this.mophnumber = mophnumber;
  }

  public String getMemberId() {  // Memberid not modified when created
    return memberid;
  }

  /**
   * same here creation date is not modified.
   */
  public Date getCreationDate() {
    return creationDate;
  }

  public int getCredits() {
    return credits;
  }

  public void setCredits(int currentcredit) {
    this.credits = currentcredit;
  }

  public List<Item> getOwnedItems() {
    return ownedItems;
  }

  public void setItems(List<Item> items) {
    this.ownedItems = items;
  }

  public int getOwnedItemsCount() {
    return ownedItems.size();
  }

  public void addItem(Item item) {
    this.ownedItems.add(item);
  }

  public void removeItem(Item item) {
    ownedItems.remove(item);
  }

  /**
   * method to get full member info.
   */
  public String getFullInformation() {
    return "name: " + name + "\nEmail: " + email + "\nCurrent Credits " + credits  
      + "\nNumber of Owned Items: " + ownedItems.size()
      + "Member ID: " + memberid;

  }

  public void payCredits(long totalPrice) {
  }

  public void earnCredits(long totalPrice) {
  }

  public void addCredits(int amount) {
    this.credits += amount;
  }

  /**
   * find item by name in member class.
   */
  public Optional<Item> getItemByName(String itemName) {
    for (Item item : ownedItems) { 
      if (item.getName().equals(itemName)) {
        return Optional.of(item);
      }
    }
    return Optional.empty();
  }

}