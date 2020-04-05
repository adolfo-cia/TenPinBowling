package com.jobsity.challenge.frames;

public interface Frame {

  int MAX_ROLL_SCORE = 10;

  String getPinFallsRepresentation();

  int getScore();

  default String getScoreRepresentation() {
    return getScore() + "\t\t";
  }
}
