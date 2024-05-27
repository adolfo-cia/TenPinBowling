package challenge;

import challenge.parse.FileParser;
import challenge.parse.Parser;
import challenge.print.ConsolePrinter;
import challenge.print.Printer;
import challenge.score.GameScorer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;

/**
 * Integration tests.
 */
public class AppTest {

    public static final String TEST_RESOURCES_PATH = "src/test/resources";
    private Printer printer;
    private Parser parser;
    private File file;
    private GameScorer gameScorer;

    @BeforeEach
    public void setUp() {
        printer = new ConsolePrinter();
        parser = new FileParser();
    }

    @Test
    public void shouldRunWith2PlayersScores() throws Exception {

        file = new File(TEST_RESOURCES_PATH + "/positive/scores.txt");
        gameScorer = new GameScorer(parser, printer);
        gameScorer.generateScore(new FileReader(file));
    }

    @Test
    public void shouldRunWithPerfectScores() throws Exception {
        file = new File(TEST_RESOURCES_PATH + "/positive/perfect.txt");
        gameScorer = new GameScorer(parser, printer);
        gameScorer.generateScore(new FileReader(file));
    }

    @Test
    public void shouldRunWithZeroScores() throws Exception {
        file = new File(TEST_RESOURCES_PATH + "/positive/zero-score.txt");
        gameScorer = new GameScorer(parser, printer);
        gameScorer.generateScore(new FileReader(file));
    }
}
