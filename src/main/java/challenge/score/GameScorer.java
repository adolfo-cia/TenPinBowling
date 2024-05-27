package challenge.score;

import challenge.model.Frame;
import challenge.model.PlayerFrames;
import challenge.parse.Parser;
import challenge.print.Printer;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

/**
 * GameScorer generates the score for each frame in a ten-pin-bowling match
 */
public class GameScorer {
    private static final int MAX_PINFALL = 10;
    private static final String FOUL = "F";
    private final Parser parser;
    private final Printer printer;

    public GameScorer(Parser parser, Printer printer) {
        this.parser = parser;
        this.printer = printer;
    }

    /**
     * Generates the score for each frame given a Readable source, and prints it to the provided Printer.
     *
     * @param readable
     * @throws Exception
     */
    public void generateScore(Readable readable) throws Exception {
        var allPlayerRolls = parser.parse(readable);

        var allPlayerFrames = new ArrayList<PlayerFrames>(allPlayerRolls.size());

        for (var playerRolls : allPlayerRolls) {
            var score = 0;
            var rollIndex = 0;
            var rolls = playerRolls.rolls();
            var frames = new ArrayList<Frame>(10);

            for (int f = 0; f < 10; f++) {
                if (isStrike(rolls, rollIndex)) {
                    score += MAX_PINFALL + strikeBonus(rolls, rollIndex);
                    var frameRolls = f != 9 ?
                            singletonList(rolls.get(rollIndex))
                            : asList(rolls.get(rollIndex), rolls.get(rollIndex + 1), rolls.get(rollIndex + 2));
                    frames.add(new Frame(frameRolls, score));
                    rollIndex += 1;

                } else if (isSpare(rolls, rollIndex)) {
                    score += MAX_PINFALL + spareBonus(rolls, rollIndex);
                    var frameRolls = f != 9 ?
                            asList(rolls.get(rollIndex), rolls.get(rollIndex + 1))
                            : asList(rolls.get(rollIndex), rolls.get(rollIndex + 1), rolls.get(rollIndex + 2));
                    frames.add(new Frame(frameRolls, score));
                    rollIndex += 2;

                } else {
                    score += toInt(rolls.get(rollIndex)) + toInt(rolls.get(rollIndex + 1));
                    frames.add(new Frame(
                            asList(rolls.get(rollIndex), rolls.get(rollIndex + 1)),
                            score));
                    rollIndex += 2;
                }
            }

            allPlayerFrames.add(new PlayerFrames(playerRolls.name(), frames));
        }

        printer.print(allPlayerFrames);
    }

    private boolean isStrike(List<String> rolls, int rollIndex) {
        return MAX_PINFALL == toInt(rolls.get(rollIndex));
    }

    private boolean isSpare(List<String> rolls, int rollIndex) {
        return MAX_PINFALL == toInt(rolls.get(rollIndex)) + toInt(rolls.get(rollIndex + 1));
    }

    private int toInt(String roll) {
        if (FOUL.equals(roll)) {
            return 0;
        }
        return parseInt(roll);
    }

    private int strikeBonus(List<String> rolls, int rollIndex) {
        return toInt(rolls.get(rollIndex + 1)) + toInt(rolls.get(rollIndex + 2));
    }

    private int spareBonus(List<String> rolls, int rollIndex) {
        return toInt(rolls.get(rollIndex + 2));
    }
}
