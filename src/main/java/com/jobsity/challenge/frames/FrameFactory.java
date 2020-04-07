package com.jobsity.challenge.frames;

import com.jobsity.challenge.game.Roll;
import java.util.List;
import java.util.Optional;

public abstract class FrameFactory {

  /**
   * Factory method for creating a StrikeFrame.
   *
   * @param rolls          list of player rolls
   * @param rollNumber     current roll number
   * @param lastFrameScore the score from the last frame
   * @return
   */
  public static Frame newStrikeFrame(
      final List<Roll> rolls, final int rollNumber, final int lastFrameScore) {

    int currentScore = lastFrameScore + getStrikeScore(rolls, rollNumber);

    return new StrikeFrame(currentScore);
  }

  /**
   * Factory method for creating a SpareFrame.
   *
   * @param rolls          list of player rolls
   * @param rollNumber     current roll number
   * @param lastFrameScore the score from the last frame
   * @return
   */
  public static Frame newSpareFrame(
      final List<Roll> rolls, final int rollNumber, final int lastFrameScore) {

    int currentScore = lastFrameScore + getSpareScore(rolls, rollNumber);

    return new SpareFrame(rolls.get(rollNumber), currentScore);
  }

  /**
   * Factory method for creating a CommonFrame.
   *
   * @param rolls          list of player rolls
   * @param rollNumber     current roll number
   * @param lastFrameScore the score from the last frame
   * @return
   */
  public static Frame newCommonFrame(
      final List<Roll> rolls, final int rollNumber, final int lastFrameScore) {

    int currentScore = lastFrameScore + getNormalScore(rolls, rollNumber);

    return new CommonFrame(rolls.get(rollNumber), rolls.get(rollNumber + 1), currentScore);
  }

  /**
   * Factory method for creating a TenthFrame.
   *
   * @param rolls          list of player rolls
   * @param rollNumber     current roll number
   * @param lastFrameScore the score from the last frame
   * @return
   */
  public static Frame newTenthFrame(
      final List<Roll> rolls, final int rollNumber, final int lastFrameScore) {

    int currentScore = lastFrameScore + getLastFrameScore(rolls, rollNumber);

    return new TenthFrame(
        rolls.get(rollNumber), rolls.get(rollNumber + 1), getLastFrameLastRoll(rolls, rollNumber),
        currentScore);
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
    int nextNextRoll = getLastFrameLastRollValue(rolls, rollNumber);

    return currentRoll + nextRoll + nextNextRoll;
  }

  private static int getLastFrameLastRollValue(final List<Roll> rolls, final int rollNumber) {
    return rolls.size() - 1 != rollNumber + 2 ? 0 : rolls.get(rollNumber + 2).getIntValue();
  }

  private static Optional<Roll> getLastFrameLastRoll(final List<Roll> rolls, final int rollNumber) {
    return rolls.size() - 1 != rollNumber + 2 ? Optional.empty()
        : Optional.of(rolls.get(rollNumber + 2));
  }
}
