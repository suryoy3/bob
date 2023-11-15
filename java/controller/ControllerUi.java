package controller;

import java.util.List;
import java.util.Optional;
import model.*;
import view.Menu;


/**
 * Represent the user interface in the console.
 */
public class ControllerUi {
  private Menu view;
  private MemberListManager memberListManager;
  private MemberIdGenerator memberIdGenerator;
  private Time time;
  private boolean isRunning;

  /**
   * Manages the member data.
   */
  public ControllerUi() {
    this.view = new Menu();
    this.memberListManager = new MemberListManager();
    this.memberIdGenerator = new MemberIdGenerator();
    time = new Time();
    this.isRunning = true;
  }


  /**
   * A flag indicating the running state of the application's main loop.
   */
  public void run() {
    while (isRunning) {
      view.displayMainMenu();
      handleMainMenuOption(view.getInput());
    }
  }

  private void handleMainMenuOption(int choice) {
    switch (choice) {
      case 1:
        handleMemberMenu();
        break;
      case 2:
        handleItemMenu();
        break;
      case 3:
        handleContractMenu();
        break;
      case 4:
        isRunning = false;
        break;
      default:
        view.showInvalidOptionMessage();
    }  // Added closing brace
  }

  private void handleMemberMenu() {
    view.displayMemberMenu();  // Fixed typo
    int choice = view.getInput();
    switch (choice) {
      case 1:
        addMember();
        break;
      case 2:
        deleteMemberById();
        break;
      case 3:
        getFullInformation();
        break;
      case 4:
        listAllMembersSimple();
        break; 
      default:
        view.showInvalidOptionMessage();
    }
  }

  private void addMember() {
    String name = view.promptForName();
    String email = view.promptForEmail();
    String mophnumber = view.promptForMoPhNumber();
    String memberId = memberIdGenerator.generateRandomId(6);

    // Check for uniqueness of email and phone number
    if (memberListManager.doesEmailExist(email)) {
      view.displayEmailExistsError();  // Use Menu's method to display error
      return;  // Exit the method
    }

    if (memberListManager.doesPhoneNumberExist(mophnumber)) {
      view.displayPhoneNumberExistsError();  // Use Menu's method to display error
      return;  // Exit the method
    }

    Member member = new Member(name, email, mophnumber, memberId);
    memberListManager.addMember(member);
    view.showSuccessMessage();
    view.displayMessage("New member added with ID: " + memberId);
  }

  private void deleteMemberById() {
    String memberId = view.getInputString("Enter member's ID to delete: ");
    String message = memberListManager.deleteMemberById(memberId);
    view.displayMessage(message);
  }
  


  private void getFullInformation() {
    String memberId = view.getInputString("Enter member's ID for detailed information: ");

    Optional<Member> optMember = memberListManager.findMemberById(memberId);

    if (optMember.isPresent()) {
      view.displayMemberInfo(optMember.get());
    } else {
      view.showMemberNotFoundMessage();
    }
  }

  private void listAllMembersSimple() {
    List<Member> allMembers = memberListManager.getAllMembers();
    if (allMembers.isEmpty()) {
      view.displayMessage("No members have been created yet.");
      return;
    }

    for (Member member : allMembers) {
      view.displaySimpleMemberInfo(member);
    }
  }


  private void handleItemMenu() {
    view.displayItemMenu();
    int choice = view.getInput();
    switch (choice) {
      case 1:
        createNewItem();
        break;
      case 2:
        deleteItem();
        break;
      case 3:
        changeItemInformation();
        break;
      case 4:
        viewItemAndContracts();
        break;
      case 5:
        return;
      default:
        view.showInvalidOptionMessage();
    }
  }

  private void createNewItem() {
    String memberId = view.getInputString("Enter member's ID to assign item to: ");
    Optional<Member> optMember = memberListManager.findMemberById(memberId);
    if (optMember.isPresent()) {
      Member member = optMember.get();
      String category = view.promptForItemCategory();
      String name = view.promptForItemNameOrId();
      String description = view.promptForItemDescription();
      int costPerDay = view.promptForItemCostPerDay();

      Item item = new Item(member, category, name, description, (int) costPerDay);
      member.addItem(item); // The member gets 100 credits in the Item's constructor.

      view.showSuccessMessage();
    } else {
      System.out.println("Member not found.");
    }
  }

  private void deleteItem() {
    String itemNameOrId = view.promptForItemNameOrId();
    
    // Find the member who owns this item
    Optional<Member> memberWithItem = memberListManager.findMemberWithItem(itemNameOrId);
    if (!memberWithItem.isPresent()) {
      view.showItemNotFoundMessage();
      return;
    }
    
    // If member found, get the item from the member
    Optional<Item> itemToRemove = memberWithItem.get().getItemByName(itemNameOrId);
    
    // Remove the item from the member's list of owned items
    if (itemToRemove.isPresent()) {
      memberWithItem.get().removeItem(itemToRemove.get());
      view.showItemRemovedMessage();
    } else {
      view.showErrorRemovingItemMessage();
    }
  }

  private void changeItemInformation() {
    String itemNameOrId = view.promptForItemNameOrId();
  
    // Find the member who owns this item
    Optional<Member> memberWithItem = memberListManager.findMemberWithItem(itemNameOrId);
    if (!memberWithItem.isPresent()) {
      view.showItemNotFoundMessage();
      return;
    }
  
    // If member found, get the item from the member
    Optional<Item> itemToChange = memberWithItem.get().getItemByName(itemNameOrId);
    if (!itemToChange.isPresent()) {
      view.showErrorRetrievingItemMessage();
      // You may need to define this method in your Menu class
      return;
    }
  
    // Prompt user for new values and update the item
    String newCategory = view.promptForNewCategory();
    String newName = view.promptForNewName();
    String newDescription = view.promptForNewDescription();
    int newCostPerDay = view.promptForNewCostPerDay();
    
    itemToChange.get().changeItemInformation(newCategory, newName, newDescription, newCostPerDay);

    view.showItemUpdatedMessage();
  }
  
    
  private void viewItemAndContracts() {
    String itemNameOrId = view.promptForItemNameOrId();

    // Find the member who owns this item
    Optional<Member> memberWithItem = memberListManager.findMemberWithItem(itemNameOrId);
    if (!memberWithItem.isPresent()) {
      view.showItemNotFoundMessage();
      return;
    }

    // If member found, get the item from the member
    Optional<Item> desiredItem = memberWithItem.get().getItemByName(itemNameOrId);
    if (!desiredItem.isPresent()) {
      view.showErrorRetrievingItemMessage();
      return;
    }

    // Display the item details
    view.displayItemInfo(desiredItem.get());

    // Display the contracts associated with the item
    List<Contract> contracts = desiredItem.get().getContracts();
    if (contracts.isEmpty()) {
      view.showNoContractsMessage();
    } else {
      for (Contract contract : contracts) {
        view.displayContractDetails(contract);
      }
    }
  }

  
  
  
         


  private void handleContractMenu() {
    view.displayContractMenu();
    int choice = view.getInput();
    switch (choice) {
      default:
        break;

    } 

  }
}

