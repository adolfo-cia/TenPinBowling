package com.jobsity.challenge.output;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import com.jobsity.challenge.frames.CommonFrame;
import com.jobsity.challenge.frames.Frame;
import com.jobsity.challenge.game.Match;
import com.jobsity.challenge.game.Player;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConsolePrinterTest {

  private Printer printer;

  @BeforeEach
  void setUp() {
    printer = new ConsolePrinter();
  }

  @Test
  void shouldPrettyPrintWithOnePlayerAndOneFrame() {
    final Frame frameMock = mock(CommonFrame.class);
    given(frameMock.getPinFallsRepresentation()).willReturn("5\t\t3");
    given(frameMock.getScoreRepresentation()).willReturn("8");

    final Player playerMock = mock(Player.class);
    given(playerMock.getName()).willReturn("test");
    given(playerMock.getFrames()).willReturn(Collections.singletonList(frameMock));

    final Match matchMock = mock(Match.class);
    given(matchMock.getPlayers()).willReturn(Collections.singletonList(playerMock));

    printer.prettyPrint(matchMock);

    then(matchMock).should(times(1)).getPlayers();
    verifyNoMoreInteractions(matchMock);

    then(playerMock).should(times(1)).getName();
    then(playerMock).should(times(1)).getFrames();
    verifyNoMoreInteractions(playerMock);

    then(frameMock).should(times(1)).getPinFallsRepresentation();
    then(frameMock).should(times(1)).getScoreRepresentation();
    verifyNoMoreInteractions(frameMock);
  }
}