package com.jobsity.challenge.frames;

import com.jobsity.challenge.game.Roll;
import java.util.List;

public abstract class FrameFactory {

  public static Frame newStrikeFrame(
      final List<Roll> rolls, final int rollNumber, final int lastFrameScore) {

    int currentScore = lastFrameScore + getStrikeScore(rolls, rollNumber);

    return new StrikeFrame(currentScore);
  }

  public static Frame newSpareFrame(
      final List<Roll> rolls, final int rollNumber, final int lastFrameScore) {

    int currentScore = lastFrameScore + getSpareScore(rolls, rollNumber);

    return new SpareFrame(rolls.get(rollNumber), currentScore);
  }

  public static Frame newCommonFrame(
      final List<Roll> rolls, final int rollNumber, final int lastFrameScore) {

    int currentScore = lastFrameScore + getNormalScore(rolls, rollNumber);

    return new CommonFrame(rolls.get(rollNumber), rolls.get(rollNumber + 1), currentScore);
  }

  public static Frame newTenthFrame(
      final List<Roll> rolls, final int rollNumber, final int lastFrameScore) {

    int currentScore = lastFrameScore + getLastFrameScore(rolls, rollNumber);

    return new TenthFrame(
        rolls.get(rollNumber), rolls.get(rollNumber + 1), rolls.get(rollNumber + 2), currentScore);
  }

  private static int getStrikeScore(final List<Roll> rolls, final int rollNumber) {

    int nextRoll = rolls.get(rollNumber + 1).getIntValue();
    int nextNextRoll = rolls.get(rollNumber + 2).getIntValue();

    return Frame.MAX_ROLL_SCORE + nextRoll + nextNextRoll;
  }

  private static int getSpareScore(final List<Roll> rolls, final int rollNumber) {

    int nextNextRoll = rolls.get(rollNumber + 2).getIntValue();

    return Frame.MAX_ROLL_SCORE + nextNextRoll;
  }

  private static int getNormalScore(final List<Roll> rolls, final int rollNumber) {

    return rolls.get(rollNumber).getIntValue() + rolls.get(rollNumber + 1).getIntValue();
  }

  private static int getLastFrameScore(final List<Roll> rolls, final int rollNumber) {

    int currentRoll = rolls.get(rollNumber).getIntValue();
    int nextRoll = rolls.get(rollNumber + 1).getIntValue();
    int nextNextRoll = rolls.get(rollNumber + 2).getIntValue();

    return currentRoll + nextRoll + nextNextRoll;
  }
}