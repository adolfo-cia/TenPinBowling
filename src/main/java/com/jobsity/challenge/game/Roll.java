package com.jobsity.challenge.game;

import org.apache.commons.lang3.math.NumberUtils;

public class Roll {

  private static final int ZERO = 0;
  private final String roll;

  private Roll(final String roll) {
    this.roll = roll;
  }

  public static Roll of(final String roll) {
    return new Roll(roll);
  }

  public String getStringValue() {
    return roll;
  }

  public int getIntValue() {
    return NumberUtils.isDigits(roll) ? Integer.parseInt(roll) : ZERO;
  }
}
