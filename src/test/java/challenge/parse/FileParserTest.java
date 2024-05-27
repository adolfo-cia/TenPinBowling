package challenge.parse;

import challenge.model.PlayerRolls;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.util.Collection;

import static challenge.AppTest.TEST_RESOURCES_PATH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileParserTest {

    @Test
    void testParseFromFileOf2Players() throws Exception {
        var parser = new FileParser();
        var allPlayerRolls = parser.parse(
                new FileReader(TEST_RESOURCES_PATH + "/positive/scores.txt"));

        assertEquals(allPlayerRolls.size(), 2);
        assertEquals(allPlayerRolls.
                stream()
                .map(PlayerRolls::rolls)
                .mapToLong(Collection::size)
                .sum(), 35);
    }

    @Test
    void testParseFromFileOf1PlayerWithPerfectScore() throws Exception {
        var parser = new FileParser();
        var allPlayerRolls = parser.parse(
                new FileReader(TEST_RESOURCES_PATH + "/positive/perfect.txt"));

        assertEquals(allPlayerRolls.size(), 1);
        assertEquals(allPlayerRolls.
                stream()
                .map(PlayerRolls::rolls)
                .mapToLong(Collection::size)
                .sum(), 12);
    }

    @Test
    void testParseFromFileOf1PlayerWithZeroScore() throws Exception {
        var parser = new FileParser();
        var allPlayerRolls = parser.parse(
                new FileReader(TEST_RESOURCES_PATH + "/positive/zero-score.txt"));

        assertEquals(allPlayerRolls.size(), 1);
        assertEquals(allPlayerRolls.
                stream()
                .map(PlayerRolls::rolls)
                .mapToLong(Collection::size)
                .sum(), 20);
    }

    @Test
    void shouldFailFromEmptyFile() throws Exception {
        var parser = new FileParser();

        assertThrows(Exception.class, () -> {
            parser.parse(
                    new FileReader(TEST_RESOURCES_PATH + "/negative/empty.txt"));
        });
    }

    @Test
    void shouldFailFromFreeText() throws Exception {
        var parser = new FileParser();

        assertThrows(Exception.class, () -> {
            parser.parse(
                    new FileReader(TEST_RESOURCES_PATH + "/negative/free-text.txt"));
        });
    }

    @Test
    void shouldFailFromInvalidRoll() throws Exception {
        var parser = new FileParser();

        assertThrows(Exception.class, () -> {
            parser.parse(
                    new FileReader(TEST_RESOURCES_PATH + "/negative/invalid-score.txt"));
        });
    }

    @Test
    void shouldFailFromNegativeRoll() throws Exception {
        var parser = new FileParser();

        assertThrows(Exception.class, () -> {
            parser.parse(
                    new FileReader(TEST_RESOURCES_PATH + "/negative/negative.txt"));
        });
    }

}