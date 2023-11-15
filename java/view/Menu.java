package view;

import java.util.Scanner;
import model.Contract;
import model.Item;
import model.Member;

/**
 * class menu.
 */
public class Menu {
  private Scanner scanner;

  public Menu() {
    this.scanner = new Scanner(System.in);
  }

  /**
   * display main menu.
   */
  public void displayMainMenu() {
    System.out.println("=== Main Menu ===");
    System.out.println("1. Member Menu");
    System.out.println("2. Item Menu");
    System.out.println("3. Contract Menu");
    System.out.println("5. Exit");
    System.out.print("Enter your choice: ");
  }

  /**
   * member Menu.
   */
  public void displayMemberMenu() {
    System.out.println("---- Member Options ----");
    System.out.println("1. Create New Member");
    System.out.println("2. Delete Member");
    System.out.println("3. View Specific Member Info");
    System.out.println("4. List All Members (Simple)");
    System.out.println("5. List All Members (Verbose)");
    System.out.print("Please choose an option (1-5): ");

  }

  public void showInvalidOptionMessage() {
    System.out.println("Invalid option. Try again.");
  }
  
  public void showMemberNotFoundMessage() {
    System.out.println("Member not found.");
  }

  public String promptForName() {
    System.out.print("Enter member's name: ");
    return scanner.nextLine();
  }

  public String promptForEmail() {
    System.out.print("Enter member email: ");
    return scanner.nextLine();
  } 

  public String promptForMoPhNumber() {
    System.out.print("Enter member mobile phone number: ");
    return scanner.nextLine();
  }

  public int promptForMemberId() {
    System.out.print("Enter member's ID: ");
    return scanner.nextInt();
  }

  public String displayEmailExistsError() {
    System.out.println("Email already exists. Please enter a unique email.");
    return scanner.nextLine();
  }

  public void displayPhoneNumberExistsError() {
    System.out.println("Email already exists. Please enter a unique email.");
   
  }


  public void showGeneratedMemberId(String id) {
    System.out.println("New member created with ID: " + id);
  }

  public void showSuccessMessage() {
    System.out.println("Operation successful!");
  }

  public void showDeleteSuccessMessage() {
    System.out.println("Member deleted successfully!");
  }

  /**
   * display get member info simple.
   */
  public void displaySimpleMemberInfo(Member member) {
    System.out.println("Name: " + member.getName());
    System.out.println("Email: " + member.getEmail());
    System.out.println("Current Credits: " + member.getCredits());
    // I'm assuming here that Member has a method like `getOwnedItemsCount()`. 
    // If not, you'd retrieve the number of owned items however it's stored in the Member class.
    System.out.println("Number of Owned Items: " + member.getOwnedItemsCount());
    System.out.println("----------------------------------");  // To separate members visually
  }


  /**
 * Display member info.
 */
  public void displayMemberInfo(Member member) {
    System.out.println("======= Member Information =======");
    System.out.println("Name: " + member.getName());
    System.out.println("Email: " + member.getEmail());
    System.out.println("Current Credits: " + member.getCredits());
    System.out.println("Number of Owned Items: " + member.getOwnedItems().size());
    System.out.println("=================================");
  }


  /**
   * display item menu.
   */
  public void displayItemMenu() {
    System.out.println("\nItem Menu:");
    System.out.println("1. Add New Item");
    System.out.println("2. Delete Item");
    System.out.println("3. Change item information");
    System.out.println("4. View item and contracts");
    System.out.println("5. Return to Main Menu");
    System.out.print("Enter your choice: ");
  }

  public String promptForItemCategory() {
    System.out.print("Enter item's category (Tool, Vehicle, Game, Toy, Sport, Other): ");
    return scanner.nextLine();
  }

  public String promptForItemNameOrId() {
    System.out.print("Enter the item's name or ID to delete: ");
    return scanner.nextLine();
  }

  public String promptForItemDescription() {
    System.out.print("Enter a short description for the item: ");
    return scanner.nextLine();
  }

  public int promptForItemCostPerDay() {
    System.out.print("Enter item's cost per day: ");
    return scanner.nextInt();
  }

  public void showItemNotFoundMessage() {
    System.out.println("Item not found.");
  }

  public void showItemRemovedMessage() {
    System.out.println("Item successfully removed!");
  }

  public void showErrorRemovingItemMessage() {
    System.out.println("Error in removing item. Try again.");
  }

  public String promptForNewCategory() {
    System.out.println("Enter new category (Tool, Vehicle, Game, Toy, Sport, Other): ");
    return scanner.nextLine();
  }

  public String promptForNewName() {
    System.out.println("Enter new name: ");
    return scanner.nextLine();
  }

  public String promptForNewDescription() {
    System.out.println("Enter new description: ");
    return scanner.nextLine();
  }

  public int promptForNewCostPerDay() {
    System.out.println("Enter new cost per day: ");
    return Integer.parseInt(scanner.nextLine());
  }

  public void showItemUpdatedMessage() {
    System.out.println("Item information updated successfully.");
  }

  public void showErrorRetrievingItemMessage() {
    System.out.println("Error retrieving item details. Please try again.");
  }

  /**
 * Displaying contract details.
 */
  public void displayContractDetails(Contract contract) {
    System.out.println("==== Contract Details ====");
    System.out.println("Start Date: " + contract.getStartDate());
    System.out.println("End Date: " + contract.getEndDate());

    Item item = contract.getItem();
    System.out.println("\nItem Details:");
    System.out.println("Item Name: " + item.getName());
    System.out.println("Item Category: " + item.getCategory());
    System.out.println("Item Description: " + item.getDescription());
    System.out.println("Cost per Day: " + item.getCostPerDay());

    Member lender = contract.getLender();
    System.out.println("\nLender Details:");
    System.out.println("Lender Name: " + lender.getName());
    System.out.println("Lender Email: " + lender.getEmail());
    System.out.println("Lender Phone: " + lender.getMoPhNumber());

    Member borrower = contract.getBorrower();
    System.out.println("\nBorrower Details:");
    System.out.println("Borrower Name: " + borrower.getName());
    System.out.println("Borrower Email: " + borrower.getEmail());
    System.out.println("Borrower Phone: " + borrower.getMoPhNumber()); // Adjust as needed.

    System.out.println("\nTotal Price/Cost: " + contract.calcuateTotalPrice());

    System.out.println("==========================");
  }

  public void showNoContractsMessage() {
    System.out.println("No contracts associated with this item.");
  }

  /**
   * displaying item info.
   */
  public void displayItemInfo(Item item) {
    System.out.println("Item Name: " + item.getName());
    System.out.println("Item Category: " + item.getCategory());
    System.out.println("Item Description: " + item.getDescription());
    System.out.println("Item Cost per Day: " + item.getCostPerDay());
    System.out.println("Item Owner: " + item.getOwner().getName());
    System.out.println("Item Creation Date: " + item.getCreationDate());
    // ... any other details you wish to display about the item
  }



  /**
   * display yhe menu for contract.
   */
  public void displayContractMenu() {
    System.out.println("\nContract Menu:");
    System.out.println("1. Create New Contract");
    System.out.println("2. Terminate Contract");
    System.out.println("3. View Specific Contract Details");
    System.out.println("4. List All Contracts");
    System.out.println("5. Return to Main Menu");
    System.out.print("Enter your choice: ");
  }


  /**
 * get input method.
 */

  public int getInput() {
    int choice = -1;
    try {
      choice = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("Please enter a valid number.");
    }
    return choice;
  }

  public String getInputString(String prompt) {
    System.out.print(prompt);
    return scanner.nextLine().trim(); // Trim the input here
}


  public void displayInvalidOptionMessage() {
    System.out.println("Invalid option. Please try again.");
  }

  public void displayMessage(String message) {
    System.out.println(message);
  }

}

