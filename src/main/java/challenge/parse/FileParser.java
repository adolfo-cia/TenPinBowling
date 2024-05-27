package challenge.parse;

import challenge.model.PlayerRolls;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Set;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toSet;
import static org.apache.commons.lang3.math.NumberUtils.isDigits;

/**
 * FileParser takes a file input as the readable source
 */
public class FileParser implements Parser {
    private static final String DELIMITER = "\t";
    private static final int MIN_ROLL = 0;
    public static final int MAX_ROLL = 10;
    public static final String FOUL = "F";
    private static final int MIN_ROLLS_COUNT = 11;
    private static final int MAX_ROLLS_COUNT = 21;

    @Override
    public Set<PlayerRolls> parse(Readable readable) throws Exception {
        var playerAndRolls = new LinkedHashMap<String, ArrayList<String>>(2);

        try (var s = new Scanner(readable)) {
            if (!s.hasNext()) throw new Exception("file is empty.");

            while (s.hasNextLine()) {
                var line = s.nextLine();
                var nameAndRoll = line.split(DELIMITER);
                if (nameAndRoll.length > 2) {
                    throw new Exception("incorrect line format");
                }

                var player = nameAndRoll[0];
                var roll = nameAndRoll[1];
                if (!isDigits(roll) && !FOUL.equals(roll)) {
                    throw new Exception("roll value is invalid");
                }
                if (isDigits(roll) && (parseInt(roll) < MIN_ROLL || parseInt(roll) > MAX_ROLL)) {
                    throw new Exception("roll number must be between 0 and 10");
                }

                playerAndRolls
                        .putIfAbsent(player, new ArrayList<>(MAX_ROLLS_COUNT));
                playerAndRolls
                        .get(player)
                        .add(roll);
            }
        }

        return playerAndRolls
                .keySet()
                .stream()
                .map(player -> {
                    var rolls = playerAndRolls.get(player);
                    if (rolls.size() < MIN_ROLLS_COUNT || rolls.size() > MAX_ROLLS_COUNT) {
                        throw new RuntimeException(
                                "player " + player + "has an invalid amount of rolls: " + rolls.size());
                    }
                    return new PlayerRolls(player, playerAndRolls.get(player));
                })
                .collect(toSet());
    }
}
