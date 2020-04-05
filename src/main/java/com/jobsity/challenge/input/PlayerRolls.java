package com.jobsity.challenge.input;

import com.jobsity.challenge.game.Roll;
import java.util.List;

public class PlayerRolls {

  private final String name;
  private final List<Roll> rolls;

  private PlayerRolls(final String name, final List<Roll> rolls) {
    this.name = name;
    this.rolls = rolls;
  }

  public static PlayerRolls of(final String name, final List<Roll> rolls) {
    return new PlayerRolls(name, rolls);
  }

  public String getName() {
    return name;
  }

  public List<Roll> getRolls() {
    return rolls;
  }
}
