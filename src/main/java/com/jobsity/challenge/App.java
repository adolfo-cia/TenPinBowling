package com.jobsity.challenge;

import com.jobsity.challenge.game.BowlingGame;
import com.jobsity.challenge.input.FileInputProcessor;
import com.jobsity.challenge.input.InputProcessor;
import com.jobsity.challenge.output.ConsolePrinter;
import com.jobsity.challenge.output.Printer;
import java.io.File;

public class App {

  /**
   * Main method of the application.
   *
   * @param args input for file name
   */
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("File name not specified.");
      System.exit(1);
    }
    File file = new File(args[0]);

    InputProcessor stringInputProcessor = new FileInputProcessor(file);
    Printer printer = new ConsolePrinter();

    BowlingGame bowlingGame = new BowlingGame(stringInputProcessor, printer);
    bowlingGame.start();
  }

}
