package com.jobsity.challenge.game;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import com.jobsity.challenge.input.InputProcessor;
import com.jobsity.challenge.input.PlayerRolls;
import com.jobsity.challenge.output.Printer;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BowlingGameTest {

  private InputProcessor inputProcessor;
  private Printer printer;
  private final String name = "test";

  @BeforeEach
  void setUp() {
    inputProcessor = mock(InputProcessor.class);
    printer = mock(Printer.class);

    final Roll roll = Roll.of("10");
    final Roll roll2 = Roll.of("7");
    final Roll roll3 = Roll.of("3");
    final Roll roll4 = Roll.of("9");
    final Roll roll5 = Roll.of("0");
    final Roll roll6 = Roll.of("10");
    final Roll roll7 = Roll.of("0");
    final Roll roll8 = Roll.of("8");
    final Roll roll9 = Roll.of("8");
    final Roll roll10 = Roll.of("2");
    final Roll roll11 = Roll.of("F");
    final Roll roll12 = Roll.of("6");
    final Roll roll13 = Roll.of("10");
    final Roll roll14 = Roll.of("10");
    final Roll roll15 = Roll.of("10");
    final Roll roll16 = Roll.of("8");
    final Roll roll17 = Roll.of("1");

    final List<Roll> rolls = Arrays
        .asList(roll, roll2, roll3, roll4, roll5, roll6, roll7, roll8, roll9, roll10, roll11,
            roll12, roll13, roll14, roll15, roll16, roll17);
    final PlayerRolls playerRolls = PlayerRolls.of(name, rolls);
    given(inputProcessor.getPlayerRolls())
        .willReturn(Collections.singletonList(playerRolls));
  }

  @Test
  void testStart() {

    BowlingGame bowlingGame = new BowlingGame(inputProcessor, printer);
    bowlingGame.start();

    then(inputProcessor).should(only()).getPlayerRolls();
    verifyNoMoreInteractions(inputProcessor);

    then(printer).should(only()).prettyPrint(any(Match.class));
    verifyNoMoreInteractions(printer);
  }
}