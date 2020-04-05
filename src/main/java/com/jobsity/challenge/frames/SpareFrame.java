package com.jobsity.challenge.frames;

import com.jobsity.challenge.game.Roll;

public class SpareFrame implements Frame {

  static final String SPARE = "/";
  private final String firstRoll;
  private final int score;

  public SpareFrame(final Roll firstRoll, final int score) {
    this.firstRoll = firstRoll.getStringValue();
    this.score = score;
  }

  public String getPinFallsRepresentation() {
    return firstRoll + "\t" + SPARE + "\t";
  }

  public int getScore() {
    return score;
  }
}
