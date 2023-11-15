package model;

import java.util.Random;

/**
 * Generate memberID.
 */
public class MemberIdGenerator {
  private final String alpha = "abcdefghijklmnopqrstuvwxyz";
  private final char[] alphanum = (alpha + alpha.toUpperCase() + "0123456789").toCharArray();
  private final Random random = new Random();

  public String generateRandomId(int length) {
    StringBuilder result = new StringBuilder();

    for (int i = 0; i < length; i++) {
        result.append(alphanum[random.nextInt(alphanum.length)]);
    }
    return result.toString();
  }
}
