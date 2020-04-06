package com.jobsity.challenge;

import com.jobsity.challenge.game.BowlingGame;
import com.jobsity.challenge.input.FileInputProcessor;
import com.jobsity.challenge.input.InputProcessor;
import com.jobsity.challenge.output.ConsolePrinter;
import com.jobsity.challenge.output.Printer;
import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Integration tests.
 */
public class AppTest {

  private static final String TEST_RESOURCES_PATH = "src/test/resources/";

  private Printer printer;
  private InputProcessor stringInputProcessor;
  private File file;
  private BowlingGame bowlingGame;

  @BeforeEach
  public void setUp() {
    printer = new ConsolePrinter();
  }

  @Test
  public void shouldRunWith2PlayersScores() {
    file = new File(TEST_RESOURCES_PATH + "two-players.txt");
    stringInputProcessor = new FileInputProcessor(file);

    bowlingGame = new BowlingGame(stringInputProcessor, printer);
    bowlingGame.start();
  }

  @Test
  public void shouldRunWithPerfectScores() {
    file = new File(TEST_RESOURCES_PATH + "perfect-score.txt");
    stringInputProcessor = new FileInputProcessor(file);

    bowlingGame = new BowlingGame(stringInputProcessor, printer);
    bowlingGame.start();
  }

  @Test
  public void shouldRunWithZeroScores() {
    file = new File(TEST_RESOURCES_PATH + "zero-score.txt");

    stringInputProcessor = new FileInputProcessor(file);

    bowlingGame = new BowlingGame(stringInputProcessor, printer);
    bowlingGame.start();
  }
}
