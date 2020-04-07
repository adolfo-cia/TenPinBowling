package com.jobsity.challenge.output;

import com.jobsity.challenge.frames.Frame;
import com.jobsity.challenge.game.Match;
import com.jobsity.challenge.game.Player;
import java.util.List;

public class ConsolePrinter implements Printer {

  private static final String FRAME = "Frame";
  private static final String PINFALLS = "Pinfalls";
  private static final String SCORE = "Score";

  @Override
  public void prettyPrint(final Match match) {
    final List<Player> players = match.getPlayers();

    System.out.print(FRAME + "\t\t");
    for (int i = 1; i <= 10; i++) {
      System.out.print(i + "\t\t");
    }
    System.out.print("\n");

    for (Player player : players) {

      final String playerName = player.getName();
      final List<Frame> playerFrames = player.getFrames();

      System.out.print(playerName + "\n");

      System.out.print(PINFALLS + "\t");
      playerFrames
          .stream()
          .map(Frame::getPinFallsRepresentation)
          .forEach(System.out::print);
      System.out.print("\n");

      System.out.print(SCORE + "\t\t");
      playerFrames
          .stream()
          .map(Frame::getScoreRepresentation)
          .forEach(System.out::print);
      System.out.print("\n");
    }
  }

}
