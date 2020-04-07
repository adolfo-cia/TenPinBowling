package com.jobsity.challenge.frames;

import static com.jobsity.challenge.frames.SpareFrame.SPARE;
import static com.jobsity.challenge.frames.StrikeFrame.STRIKE;
import static com.jobsity.challenge.input.InputProcessor.FOUL;
import static java.lang.Integer.parseInt;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;
import static org.apache.commons.lang3.math.NumberUtils.isDigits;

import com.jobsity.challenge.game.Roll;
import java.util.Optional;

public class TenthFrame implements Frame {

  private final String firstRoll;
  private final String secondRoll;
  private final String thirdRoll;
  private final int score;

  /**
   * Constructor of TenthFrame.
   *
   * @param firstRoll  the first roll of the frame
   * @param secondRoll the second roll of the frame
   * @param thirdRoll  the third roll of the frame, if any
   * @param score      the score of the frame
   */
  public TenthFrame(
      final Roll firstRoll, final Roll secondRoll, final Optional<Roll> thirdRoll,
      final int score) {
    this.firstRoll = firstRoll.getStringValue();
    this.secondRoll = secondRoll.getStringValue();
    this.thirdRoll = thirdRoll.map(Roll::getStringValue).orElse("");
    this.score = score;
  }

  @Override
  public String getPinFallsRepresentation() {
    String first = getFirstRollRepresentation();
    String second = getSecondRollRepresentation();
    String third = getThirdRollRepresentation();

    return first + "\t" + second + "\t" + third;
  }

  @Override
  public int getScore() {
    return score;
  }

  private String getFirstRollRepresentation() {
    String roll;
    if (isDigits(firstRoll) && parseInt(firstRoll) == MAX_ROLL_SCORE) {
      roll = STRIKE;
    } else {
      roll = firstRoll;
    }
    return roll;
  }

  private String getSecondRollRepresentation() {
    String roll;
    if (FOUL.equals(secondRoll)) {
      roll = secondRoll;
    } else if (parseInt(secondRoll) == MAX_ROLL_SCORE) {
      roll = STRIKE;
    } else if ((parseInt(secondRoll) != INTEGER_ZERO && isDigits(firstRoll))
        && parseInt(firstRoll) + parseInt(secondRoll) == MAX_ROLL_SCORE) {
      roll = SPARE;
    } else {
      roll = secondRoll;
    }
    return roll;
  }

  private String getThirdRollRepresentation() {
    String roll;
    if (isEmpty(thirdRoll)) {
      roll = "";
    } else if (FOUL.equals(thirdRoll)) {
      roll = thirdRoll;
    } else if (parseInt(thirdRoll) == MAX_ROLL_SCORE) {
      roll = STRIKE;
    } else if (
        (!isDigits(firstRoll) || parseInt(firstRoll) == INTEGER_ZERO)
            && parseInt(secondRoll) + parseInt(thirdRoll) == MAX_ROLL_SCORE) {
      roll = SPARE;
    } else if (
        (isDigits(firstRoll) && isDigits(secondRoll)
            && parseInt(firstRoll) + parseInt(secondRoll) != MAX_ROLL_SCORE)
            && parseInt(secondRoll) + parseInt(thirdRoll) == MAX_ROLL_SCORE) {
      roll = SPARE;
    } else {
      roll = thirdRoll;
    }
    return roll;
  }
}
