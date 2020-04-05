package com.jobsity.challenge.frames;

import com.jobsity.challenge.game.Roll;

public class CommonFrame implements Frame {

  private final String firstRoll;
  private final String secondRoll;
  private final int score;

  public CommonFrame(final Roll firstRoll, final Roll secondRoll, final int score) {
    this.firstRoll = firstRoll.getStringValue();
    this.secondRoll = secondRoll.getStringValue();
    this.score = score;
  }

  public String getPinFallsRepresentation() {
    return firstRoll + "\t" + secondRoll + "\t";
  }

  public int getScore() {
    return score;
  }
}
