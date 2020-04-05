package com.jobsity.challenge.output;

import com.jobsity.challenge.frames.Frame;
import com.jobsity.challenge.game.Match;
import com.jobsity.challenge.game.Player;

public class ConsolePrinter implements Printer {

  private static final String FRAME = "Frame";
  private static final String PINFALLS = "Pinfalls";
  private static final String SCORE = "Score";

  public void prettyPrint(final Match match) {

    System.out.print(FRAME + "\t\t");
    for (int i = 1; i <= 10; i++) {
      System.out.print(i + "\t\t");
    }
    System.out.print("\n");
    for (int i = 0; i < match.getPlayers().size(); i++) {
      Player player = match.getPlayers().get(i);
      System.out.print(player.getName() + "\n");
      System.out.print(PINFALLS + "\t");
      player.getFrames().stream().map(Frame::getPinFallsRepresentation).forEach(System.out::print);
      System.out.print("\n");
      System.out.print(SCORE + "\t\t");
      player.getFrames().stream().map(Frame::getScoreRepresentation).forEach(System.out::print);
      System.out.print("\n");
    }
  }
}
