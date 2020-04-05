package com.jobsity.challenge;

import com.jobsity.challenge.game.BowlingGame;
import com.jobsity.challenge.input.InputProcessor;
import com.jobsity.challenge.input.StringInputProcessor;
import com.jobsity.challenge.output.ConsolePrinter;
import com.jobsity.challenge.output.Printer;

public class App {

  public static void main(String[] args) {

    InputProcessor stringInputProcessor = new StringInputProcessor();
    Printer printer = new ConsolePrinter();
    BowlingGame bowlingGame = new BowlingGame(stringInputProcessor, printer);
    bowlingGame.start();

  }

}
