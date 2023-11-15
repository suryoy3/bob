package model;

import java.util.Date;

/**
 * creating class called Contract.
 */

public class Contract {
  private Date startDate;
  private Date endDate;
  private Item item;
  private Member lender;
  private Member borrower;
  private long totalPrice;

  /**
   * Constructor.
   */

  public Contract(Date startDate, Date endDate, Item item, Member lender, Member borrower) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.item = item;
    this.lender = lender;
    this.borrower = borrower;
    this.totalPrice = calcuateTotalPrice();
    contractTransaction();
  }
  


  /**
   * Calculate the total price based on the number of days and item's cost per day.
   * Using long because it can store larger values than int
   */
  public int calcuateTotalPrice() {
    long numberOfDays = (endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000);
    this.totalPrice = numberOfDays * item.getCostPerDay();
    return (int) totalPrice;
  }
  
  
  /**
   * method to transantion.
   */

  private void contractTransaction() {
    if (lender.getCredits() < totalPrice) {
      throw new IllegalArgumentException("Lender does not have enough credits.");
    }


    lender.earnCredits(totalPrice);
    borrower.payCredits(totalPrice);

    item.getContracts().add(this);
  }

  public Date getStartDate() {
    return startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public Item getItem() {
    return this.item;
  }

  public Member getLender() {
    return this.lender;
  }

  public Member getBorrower() {
    return this.borrower;
  }



}