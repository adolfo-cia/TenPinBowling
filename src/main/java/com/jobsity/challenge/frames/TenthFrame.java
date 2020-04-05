package com.jobsity.challenge.frames;

import static com.jobsity.challenge.frames.SpareFrame.SPARE;
import static com.jobsity.challenge.frames.StrikeFrame.STRIKE;
import static com.jobsity.challenge.input.InputProcessor.FOUL;
import static java.lang.Integer.parseInt;

import com.jobsity.challenge.game.Roll;

public class TenthFrame implements Frame {

  private final String firstRoll;
  private final String secondRoll;
  private final String thirdRoll;
  private final int score;

  public TenthFrame(
      final Roll firstRoll, final Roll secondRoll, final Roll thirdRoll, final int score) {
    this.firstRoll = firstRoll.getStringValue();
    this.secondRoll = secondRoll.getStringValue();
    this.thirdRoll = thirdRoll.getStringValue();
    this.score = score;
  }

  public String getPinFallsRepresentation() {
    String first = getFirstRollRepresentation();
    String second = getSecondRollRepresentation();
    String third = getThirdRollRepresentation();

    return first + "\t" + second + "\t" + third;
  }

  public int getScore() {
    return score;
  }

  private String getFirstRollRepresentation() {
    return getRollRepresentation(firstRoll, "0");
  }

  private String getSecondRollRepresentation() {
    return getRollRepresentation(secondRoll, firstRoll);
  }

  private String getThirdRollRepresentation() {
    return getRollRepresentation(thirdRoll, secondRoll);
  }

  private String getRollRepresentation(final String currentRoll, final String previousRoll) {
    String roll;
    if (FOUL.equals(currentRoll)) {
      roll = thirdRoll;
    } else if (parseInt(currentRoll) == MAX_ROLL_SCORE) {
      roll = STRIKE;
    } else if (parseInt(previousRoll) + parseInt(currentRoll) == MAX_ROLL_SCORE) {
      roll = SPARE;
    } else {
      roll = currentRoll;
    }
    return roll;
  }
}
