package challenge.print;

import challenge.model.Frame;
import challenge.model.PlayerFrames;

import java.util.List;
import java.util.function.Function;

/**
 * ConsolePrinter prints the outcome of a match to the console.
 */
public class ConsolePrinter implements Printer {
    private static final String FRAME = "Frame";
    private static final String PINFALLS = "Pinfalls";
    private static final String SCORE = "Score";
    private static final String STRIKE = "X";
    private static final String MAX_PINFALL = "10";
    private static final String SPARE = "/";

    @Override
    public void print(List<PlayerFrames> allPlayerFrames) {

        System.out.print(FRAME + "\t\t");
        for (int i = 1; i <= 10; i++) {
            System.out.print(i + "\t\t");
        }
        System.out.print("\n");

        for (var playerFrame : allPlayerFrames) {

            var playerName = playerFrame.name();
            var playerFrames = playerFrame.frames();

            System.out.print(playerName + "\n");

            System.out.print(PINFALLS + "\t");
            playerFrames
                    .stream()
                    .map(drawFrame)
                    .forEach(System.out::print);
            System.out.print("\n");

            System.out.print(SCORE + "\t\t");
            playerFrames
                    .stream()
                    .map(drawScore)
                    .forEach(System.out::print);
            System.out.print("\n");
        }
    }

    private final Function<Frame, String> drawFrame = frame -> {
        var rolls = frame.rolls();
        if (rolls.size() == 1) return "\t" + STRIKE + "\t";
        if (rolls.size() == 2) {
            return MAX_PINFALL.equals(Integer.toString(frame.sumFirstTwoRolls())) ?
                    rolls.get(0) + "\t" + SPARE + "\t"
                    : rolls.get(0) + "\t" + rolls.get(1) + "\t";
        }
        if (rolls.size() == 3) {
            return resolveThreeRollsFrame(frame);
        }
        throw new RuntimeException("invalid rolls size: " + rolls.size());
    };

    private final Function<Frame, String> drawScore = frame -> frame.score() + "\t\t";

    private String resolveThreeRollsFrame(Frame frame) {
        var sumFirstTwoRolls = Integer.toString(frame.sumFirstTwoRolls());
        var rolls = frame.rolls();
        return MAX_PINFALL.equals(sumFirstTwoRolls) ?
                rolls.get(0) + "\t" + SPARE + "\t" + maybeStrike(rolls.get(2))
                : maybeStrike(rolls.get(0)) +
                "\t" +
                maybeStrike(rolls.get(1)) +
                "\t" +
                maybeStrike(rolls.get(2));
    }

    private String maybeStrike(String roll) {
        if (MAX_PINFALL.equals(roll)) {
            return STRIKE;
        }
        return roll;
    }

}
