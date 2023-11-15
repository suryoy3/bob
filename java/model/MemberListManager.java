package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * Creating a list that holds Member.
 */
public class MemberListManager {
  private final List<Member> memberList;


  /**
   * Constructor.
   */
  public MemberListManager() {
    this.memberList = new ArrayList<>();
  }

  /**
   * Method to add member to List.
   */
  public boolean addMember(Member member) {
    return memberList.add(member);
  }
  

  /**
    * Delete a memebr using their unique ID.
    */
  public String deleteMemberById(String memberId) {
    String trimmedId = memberId.trim(); // Trim the memberId to remove leading/trailing spaces
    Iterator<Member> iterator = memberList.iterator();
    while (iterator.hasNext()) {
      Member member = iterator.next();
      if (member.getMemberId().equals(trimmedId)) {
        iterator.remove();
        return "Member with ID: " + trimmedId + " has been deleted.";
      }
    }
    return "Member with ID: " + trimmedId + " not found.";
  }
    
  

  /**
   * Mehtod to update Member.
   */
  public void updateMemberInfo(String memberId, String newName, String newEmail,
        String newMoPhoNumber) {
    for (Member member : memberList) {
      if (member.getMemberId().equals(memberId)) {
        member.setName(newName);
        member.setEmail(newEmail);
        member.setMoPhNumber(newMoPhoNumber);
        System.out.println("Member with ID: " + memberId + " has been updated.");
        return;
      }
    }
    System.out.println("Member with ID: " + memberId + " not found.");
  }

  /**
   * Method to check if an email already exist in the member list.
   */
  public boolean doesEmailExist(String email) {
    for (Member member : memberList) {
      if (member.getEmail().equalsIgnoreCase(email)) {
        return true;
      }
    }
    return false;
  }

  /**
    * Check if a phone number already exists in the member list.
    */
  public boolean doesPhoneNumberExist(String phoneNumber) {
    for (Member member : memberList) {
      if (member.getMoPhNumber().equals(phoneNumber)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Find a member by their ID (useful for displaying information in the UI).
   */
  public Optional<Member> findMemberById(String memberId) {
    for (Member member : memberList) {
      if (member.getMemberId().equals(memberId)) {
        return Optional.of(member);
      }
    }
    return Optional.empty();
  }

  
  /**
     * List all members.
     */
  public List<Member> getAllMembers() {
    return memberList;
  }

  /**
   * find memebers by the items name.
   */
  public Optional<Member> findMemberWithItem(String itemName) {
    for (Member member : memberList) { 
      Optional<Item> item = member.getItemByName(itemName);
      if (item.isPresent()) {
        return Optional.of(member);
      }
    }
    return Optional.empty();
  }
 
}