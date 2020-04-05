package com.jobsity.challenge.game;

import com.jobsity.challenge.frames.Frame;
import java.util.ArrayList;
import java.util.List;

public class Player {

  private final String name;
  private final List<Frame> gameFrames = new ArrayList<>();

  private Player(final String name) {
    this.name = name;
  }

  public static Player of(final String name) {
    return new Player(name);
  }

  public String getName() {
    return name;
  }

  public void addFrame(final Frame frame) {
    gameFrames.add(frame);
  }

  public List<Frame> getFrames() {
    return gameFrames;
  }
}
