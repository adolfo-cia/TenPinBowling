package com.jobsity.challenge.input;

import static java.util.stream.Collectors.toList;

import com.jobsity.challenge.game.Roll;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Processor class that handles the parsing of the input source.
 */
public abstract class InputProcessor {

  private static final String DELIMITER = " ";
  private static final int MIN_ROLL = 0;
  public static final int MAX_ROLL = 10;
  private static final int MIN_ROLL_COUNT = 11;
  private static final int MAX_ROLL_COUNT = 21;
  public static final String FOUL = "F";

  /**
   * Main method of the processor. Will return the parsed input into a list of {@link PlayerRolls}
   *
   * @return playerRolls list
   */
  public abstract List<PlayerRolls> getPlayerRolls();

  protected void processLine(final String line, final Map<String, List<String>> nameAndRolls) {
    final String[] nameAndRoll = getParsedLine(line);
    final String name = getName(nameAndRoll);
    final String roll = getRoll(nameAndRoll);

    addPlayerAndRoll(name, roll, nameAndRolls);
  }

  protected List<PlayerRolls> createPlayerRollsList(final Map<String, List<String>> nameAndRolls) {
    final List<PlayerRolls> playerRollsList = new ArrayList<>();
    nameAndRolls.forEach(
        (name, rolls) -> {
          List<Roll> rollsList = rolls.stream().map(Roll::of).collect(toList());
          PlayerRolls playerRolls = PlayerRolls.of(name, rollsList);
          playerRollsList.add(playerRolls);
        });
    return playerRollsList;
  }

  protected void addPlayerAndRoll(
      final String name, String roll, final Map<String, List<String>> nameAndRolls) {
    final List<String> rolls = nameAndRolls.getOrDefault(name, new ArrayList<>());
    rolls.add(roll);
    nameAndRolls.put(name, rolls);
  }

  protected String[] getParsedLine(final String line) {
    final String[] nameAndRoll = line.split(DELIMITER);
    if (nameAndRoll.length > 2) {
      throw new RuntimeException("Incorrect Line format");
    }
    return nameAndRoll;
  }

  protected String getName(final String[] nameAndRoll) {
    return nameAndRoll[0];
  }

  protected String getRoll(final String[] nameAndRoll) {
    final String roll = nameAndRoll[1];
    if (NumberUtils.isDigits(roll)
        && (Integer.parseInt(roll) < MIN_ROLL || Integer.parseInt(roll) > MAX_ROLL)) {
      throw new RuntimeException("Roll number is invalid");
    } else if (StringUtils.isAlpha(roll) && !FOUL.equals(roll)) {
      throw new RuntimeException("Roll letter is invalid");
    }

    return roll;
  }

  protected void validateRollCounts(final Map<String, List<String>> nameAndRolls) {
    nameAndRolls.forEach(
        (name, rolls) -> {
          if (rolls.size() < MIN_ROLL_COUNT || rolls.size() > MAX_ROLL_COUNT) {
            throw new RuntimeException("Invalid number of rolls");
          }
        });
  }
}
