package challenge.print;

import challenge.model.PlayerFrames;

import java.util.List;

/**
 * Printer for the outcome of a ten-pin-bowling match
 */
public interface Printer {
    /**
     * Prints the outcome the match.
     *
     * @param allPlayerFrames
     */
    void print(List<PlayerFrames> allPlayerFrames);
}
