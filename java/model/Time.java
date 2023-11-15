package model;

/**
 * Creating class Time.
 */
public class Time {

  private int currentDay; // Initial day counter

  /**
     * Constructor initializes the day counter to 0 (the first day).
     */
  public Time() {
    this.currentDay = 0;
  }

  /**
     * Gets the current day counter.
     * the current day
     */
  public int getCurrentDay() {
    return currentDay;
  }

  /**
     * Advances to the next day. Increments the day counter by 1.
     */
  public void advanceDay() {
    currentDay++;
    System.out.println("Day advanced to: " + currentDay);
  }
    
}
