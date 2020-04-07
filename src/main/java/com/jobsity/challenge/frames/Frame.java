package com.jobsity.challenge.frames;

public interface Frame {

  int MAX_ROLL_SCORE = 10;

  /**
   * The visual representation of the rolls for the scoreboard.
   *
   * @return string representation for scoreboard
   */
  String getPinFallsRepresentation();

  /**
   * The score of the frame.
   *
   * @return score
   */
  int getScore();

  /**
   * The visual representation of the score.
   *
   * @return string representation of score
   */
  default String getScoreRepresentation() {
    return getScore() + "\t\t";
  }
}
