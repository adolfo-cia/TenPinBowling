package com.jobsity.challenge.input;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StringInputProcessor extends InputProcessor {

  private static final String INPUT_STRING =
      "Jeff 10\n"
          + "John 3\n"
          + "John 7\n"
          + "Jeff 7\n"
          + "Jeff 3\n"
          + "John 6\n"
          + "John 3\n"
          + "Jeff 9\n"
          + "Jeff 0\n"
          + "John 10\n"
          + "Jeff 10\n"
          + "John 8\n"
          + "John 1\n"
          + "Jeff 0\n"
          + "Jeff 8\n"
          + "John 10\n"
          + "Jeff 8\n"
          + "Jeff 2\n"
          + "John 10\n"
          + "Jeff F\n"
          + "Jeff 6\n"
          + "John 9\n"
          + "John 0\n"
          + "Jeff 10\n"
          + "John 7\n"
          + "John 3\n"
          + "Jeff 10\n"
          + "John 4\n"
          + "John 4\n"
          + "Jeff 10\n"
          + "Jeff 8\n"
          + "Jeff 1\n"
          + "John 10\n"
          + "John 9\n"
          + "John 0";

  public List<PlayerRolls> getPlayerRolls() {

    final Map<String, List<String>> nameAndRolls = new HashMap<>();

    try (Scanner scanner = new Scanner(INPUT_STRING)) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        processLine(line, nameAndRolls);
      }
    }

    validateRollCounts(nameAndRolls);

    return createPlayerRollsList(nameAndRolls);
  }
}
