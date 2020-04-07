package com.jobsity.challenge.output;

import com.jobsity.challenge.game.Match;

/**
 * Printer interface that defines what printer implementations should do.
 */
public interface Printer {

  /**
   * Main method of the printer. Implementors should define and print the contents of {@link Match}
   *
   * @param match the match of a bownling game
   */
  void prettyPrint(final Match match);
}
