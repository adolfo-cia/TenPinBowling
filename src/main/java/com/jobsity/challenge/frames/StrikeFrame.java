package com.jobsity.challenge.frames;

public class StrikeFrame implements Frame {

  static final String STRIKE = "X";
  private final int score;

  public StrikeFrame(final int score) {
    this.score = score;
  }

  @Override
  public String getPinFallsRepresentation() {
    return "\t" + STRIKE + "\t";
  }

  @Override
  public int getScore() {
    return score;
  }
}
