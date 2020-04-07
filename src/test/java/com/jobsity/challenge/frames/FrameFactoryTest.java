package com.jobsity.challenge.frames;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jobsity.challenge.game.Roll;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class FrameFactoryTest {

  @Test
  void testNewStrikeFrame() {
    final Roll roll = Roll.of("10");
    final Roll roll2 = Roll.of("5");
    final Roll roll3 = Roll.of("4");
    final List<Roll> rolls = Arrays.asList(roll, roll2, roll3);
    int rollNumber = 0;
    int lastFrameScore = 10;
    final Frame frame = FrameFactory.newStrikeFrame(rolls, rollNumber, lastFrameScore);
    assertEquals(frame.getScore(), 29);
    assertEquals(frame.getScoreRepresentation(), "29\t\t");
    assertEquals(frame.getPinFallsRepresentation(), "\tX\t");
  }

  @Test
  void testNewSpareFrame() {
    final Roll roll = Roll.of("7");
    final Roll roll2 = Roll.of("3");
    final Roll roll3 = Roll.of("4");
    final List<Roll> rolls = Arrays.asList(roll, roll2, roll3);
    int rollNumber = 0;
    int lastFrameScore = 10;
    final Frame frame = FrameFactory.newSpareFrame(rolls, rollNumber, lastFrameScore);
    assertEquals(frame.getScore(), 24);
    assertEquals(frame.getScoreRepresentation(), "24\t\t");
    assertEquals(frame.getPinFallsRepresentation(), "7\t/\t");
  }

  @Test
  void testNewCommonFrame() {
    final Roll roll = Roll.of("6");
    final Roll roll2 = Roll.of("3");
    final List<Roll> rolls = Arrays.asList(roll, roll2);
    final int rollNumber = 0;
    final int lastFrameScore = 10;
    final Frame frame = FrameFactory.newCommonFrame(rolls, rollNumber, lastFrameScore);
    assertEquals(frame.getScore(), 19);
    assertEquals(frame.getScoreRepresentation(), "19\t\t");
    assertEquals(frame.getPinFallsRepresentation(), "6\t3\t");
  }

  @Test
  void testNewTenthFrame() {
    final Roll roll = Roll.of("10");
    final Roll roll2 = Roll.of("8");
    final Roll roll3 = Roll.of("1");
    final List<Roll> rolls = Arrays.asList(roll, roll2, roll3);
    final int rollNumber = 0;
    final int lastFrameScore = 10;
    final Frame frame = FrameFactory.newTenthFrame(rolls, rollNumber, lastFrameScore);
    assertEquals(frame.getScore(), 29);
    assertEquals(frame.getScoreRepresentation(), "29\t\t");
    assertEquals(frame.getPinFallsRepresentation(), "X\t8\t1");
  }
}