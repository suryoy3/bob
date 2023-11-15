package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Attributes.
 */

public class Item {
  private String name;
  private String creationDate;
  private String category;
  private String description;
  private int costPerDay;
  private List<Contract> contracts;
  private boolean available;
  private Member owner;
  private final CategoryValidator categoryValidator = new CategoryValidator();


  /**
   * Constructor.
   */
  public Item(Member owner, String category, String name, String description, int costPerDay) {
    this.owner = owner;
    this.name = name;
    setCategory(category);
    this.description = description;
    this.creationDate = getCurrentDate();
    this.costPerDay = costPerDay;
    this.contracts = new ArrayList<>();
    this.available = true;
    this.owner.addCredits(100);
  }


  public String getCategory() {
    return category;
  }

  private void setCategory(String category) {
    if (categoryValidator.isValidCategory(category)) {
      this.category = category;
    } else {
      throw new IllegalArgumentException("Invalid category: " + category);
    }
  }


  private String getCurrentDate() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date currentDate = new Date();
    return dateFormat.format(currentDate);
  }


  // Method to delete an item
  public void deleteItem() {
    markAsUnavailable();
  }

  /**
   * method to update information of the item.
   */
  public void changeItemInformation(String category, String name, String description,
       int costPerDay) {
    setCategory(category);
    this.name = name;
    this.description = description;
    this.costPerDay = costPerDay;
  }



  /**
     * Method to check if the item is available for lending.
     */
  public boolean isAvailable() {
    return available;
  }

  /**
     * Method to mark the item as unavailable.
     */
  public void markAsUnavailable() {
    available = false;
  }

  // Method to mark the item as available again (e.g., when a contract ends)
  public void markAsAvailable() {
    available = true;
  }


  public int getCostPerDay() {
    return costPerDay;
  }

  public void setCostPerDay(int costPerDay) {
    this.costPerDay = costPerDay;
  }

  public String getName() {
    return name;
  }
    
  public void setName(String name) {
    this.name = name;

  }

  public String getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(String creationDate) {
    this.creationDate = creationDate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public List<Contract> getContracts() {
    return contracts;
  }

  public void setContracts(List<Contract> contracts) {
    this.contracts = contracts;
  }

  public Member getOwner() {
    return owner;
  }

  public void setOwner(Member owner) {
    this.owner = owner;
  }

}

 