package com.jobsity.challenge.input;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FileInputProcessor extends InputProcessor {

  private final File file;

  public FileInputProcessor(File file) {
    this.file = file;
  }

  public List<PlayerRolls> getPlayerRolls() {

    final Map<String, List<String>> nameAndRolls = new HashMap<>();

    try (Scanner scanner = new Scanner(file)) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        processLine(line, nameAndRolls);
      }

    } catch (FileNotFoundException e) {
      System.err.println("Cannot open file.");
      System.exit(1);
    }

    validateRollCounts(nameAndRolls);

    return createPlayerRollsList(nameAndRolls);
  }
}
