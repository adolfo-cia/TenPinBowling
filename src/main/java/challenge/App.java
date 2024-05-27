package challenge;

import challenge.parse.FileParser;
import challenge.print.ConsolePrinter;
import challenge.score.GameScorer;

import java.io.FileReader;

public class App {

    /**
     * Main method of the application.
     *
     * @param args input for file name
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("only a file name is required.");
            System.exit(1);
        }

        var parser = new FileParser();
        var printer = new ConsolePrinter();
        var gameScorer = new GameScorer(parser, printer);

        try {
            gameScorer.generateScore(new FileReader(args[0]));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
