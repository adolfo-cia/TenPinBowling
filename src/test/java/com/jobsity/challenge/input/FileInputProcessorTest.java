package com.jobsity.challenge.input;

import static com.jobsity.challenge.AppTest.TEST_RESOURCES_PATH;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;

class FileInputProcessorTest {

  private File file;
  private InputProcessor fileInputProcessor;

  @Test
  void shouldFind2PlayersAnd35RollsWhenGetPlayerRollsForTwoPlayers() {
    file = new File(TEST_RESOURCES_PATH + "two-players.txt");
    fileInputProcessor = new FileInputProcessor(file);

    final List<PlayerRolls> playerRollsList = fileInputProcessor.getPlayerRolls();

    assertEquals(playerRollsList.size(), 2);
    assertEquals(
        playerRollsList
            .stream()
            .map(PlayerRolls::getRolls)
            .flatMap(Collection::stream)
            .count(),
        35);
  }

  @Test
  void shouldFind1PlayersAnd12RollsWhenGetPlayerRollsForOnePlayerAndPerfectScore() {
    file = new File(TEST_RESOURCES_PATH + "perfect-score.txt");
    fileInputProcessor = new FileInputProcessor(file);

    final List<PlayerRolls> playerRollsList = fileInputProcessor.getPlayerRolls();

    assertEquals(playerRollsList.size(), 1);
    assertEquals(
        playerRollsList
            .stream()
            .map(PlayerRolls::getRolls)
            .flatMap(Collection::stream)
            .count(),
        12);
  }

  @Test
  void shouldFind1PlayersAnd20RollsWhenGetPlayerRollsForOnePlayerAndZeroScore() {
    file = new File(TEST_RESOURCES_PATH + "zero-score.txt");
    fileInputProcessor = new FileInputProcessor(file);

    final List<PlayerRolls> playerRollsList = fileInputProcessor.getPlayerRolls();

    assertEquals(playerRollsList.size(), 1);
    assertEquals(
        playerRollsList
            .stream()
            .map(PlayerRolls::getRolls)
            .flatMap(Collection::stream)
            .count(),
        20);
  }
}