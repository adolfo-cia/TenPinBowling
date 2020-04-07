package com.jobsity.challenge.frames;

import com.jobsity.challenge.game.Roll;

public class CommonFrame implements Frame {

  private final String firstRoll;
  private final String secondRoll;
  private final int score;

  /**
   * Constructor of CommonFrame.
   *
   * @param firstRoll  the first roll of this frame
   * @param secondRoll the second roll of this frame
   * @param score      the score of the frame
   */
  public CommonFrame(final Roll firstRoll, final Roll secondRoll, final int score) {
    this.firstRoll = firstRoll.getStringValue();
    this.secondRoll = secondRoll.getStringValue();
    this.score = score;
  }

  @Override
  public String getPinFallsRepresentation() {
    return firstRoll + "\t" + secondRoll + "\t";
  }

  @Override
  public int getScore() {
    return score;
  }
}
