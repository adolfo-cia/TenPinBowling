package challenge.parse;

import challenge.model.PlayerRolls;

import java.util.Set;

/**
 * Parser for the input of a ten-pin-bowling match.
 */
public interface Parser {
    /**
     * Parse a readable input into a set of PlayerRolls
     *
     * @param readable
     * @return a set of PlayerRolls representing all the rolls for each player.
     * @throws Exception
     */
    Set<PlayerRolls> parse(Readable readable) throws Exception;
}
