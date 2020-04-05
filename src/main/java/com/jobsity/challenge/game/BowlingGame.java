package com.jobsity.challenge.game;

import static com.jobsity.challenge.input.InputProcessor.MAX_ROLL;

import com.jobsity.challenge.frames.Frame;
import com.jobsity.challenge.frames.FrameFactory;
import com.jobsity.challenge.input.InputProcessor;
import com.jobsity.challenge.input.PlayerRolls;
import com.jobsity.challenge.output.Printer;
import java.util.ArrayList;
import java.util.List;

public class BowlingGame {

  private final InputProcessor inputProcessor;
  private final Printer printer;

  public BowlingGame(InputProcessor inputProcessor, Printer printer) {
    this.inputProcessor = inputProcessor;
    this.printer = printer;
  }

  public void start() {
    final List<PlayerRolls> playerRolls = inputProcessor.getPlayerRolls();
    final List<Player> players = new ArrayList<>();

    Player player;
    List<Roll> rolls;
    int rollNumber;
    int previousScore;
    Frame tenthFrame;

    for (PlayerRolls playerRoll : playerRolls) {
      player = Player.of(playerRoll.getName());
      rolls = playerRoll.getRolls();
      rollNumber = 0;
      previousScore = 0;
      for (int frame = 0; frame < 9; frame++) {
        if (isStrike(rolls, rollNumber)) {
          Frame strikeFrame = FrameFactory.newStrikeFrame(rolls, rollNumber, previousScore);
          player.addFrame(strikeFrame);
          previousScore = strikeFrame.getScore();
          rollNumber++;
        } else if (isSpare(rolls, rollNumber)) {
          Frame spareFrame = FrameFactory.newSpareFrame(rolls, rollNumber, previousScore);
          player.addFrame(spareFrame);
          previousScore = spareFrame.getScore();
          rollNumber += 2;
        } else {
          Frame commonFrame = FrameFactory.newCommonFrame(rolls, rollNumber, previousScore);
          player.addFrame(commonFrame);
          previousScore = commonFrame.getScore();
          rollNumber += 2;
        }
      }
      tenthFrame = FrameFactory.newTenthFrame(rolls, rollNumber, previousScore);
      player.addFrame(tenthFrame);

      players.add(player);
    }

    final Match match = Match.of(players);

    printer.prettyPrint(match);
  }

  private boolean isStrike(final List<Roll> rolls, final int rollNumber) {
    return rolls.get(rollNumber).getIntValue() == MAX_ROLL;
  }

  private boolean isSpare(final List<Roll> rolls, final int rollNumber) {
    return rolls.get(rollNumber).getIntValue() + rolls.get(rollNumber + 1).getIntValue()
        == MAX_ROLL;
  }
}
