package com.jobsity.challenge.game;

import java.util.List;

public class Match {

  private final List<Player> players;

  private Match(final List<Player> players) {
    this.players = players;
  }

  public static Match of(final List<Player> players) {
    return new Match(players);
  }

  public List<Player> getPlayers() {
    return players;
  }
}
