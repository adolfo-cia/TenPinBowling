package challenge.score;

import challenge.model.Frame;
import challenge.model.PlayerFrames;
import challenge.model.PlayerRolls;
import challenge.parse.FileParser;
import challenge.parse.Parser;
import challenge.print.Printer;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static challenge.AppTest.TEST_RESOURCES_PATH;
import static java.util.Arrays.asList;
import static java.util.Collections.nCopies;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

class GameScorerTest {

    @Test
    void testGenerateScoreFor2Players() throws Exception {
        var parser = new FileParser();
        spy(parser);
        var printer = mock(Printer.class);

        GameScorer gs = new GameScorer(parser, printer);
        gs.generateScore(new FileReader(TEST_RESOURCES_PATH + "/positive/scores.txt"));

        then(printer).should(only()).print(anyList());
        verify(printer).print(argThat(list -> {
            assertEquals(list.size(), 2);
            assertEquals(
                    list.stream()
                            .filter(pf -> "Jeff".equals(pf.name())).count(), 1);
            assertEquals(
                    list.stream()
                            .filter(pf -> "John".equals(pf.name())).count(), 1);
            assertEquals(
                    list.stream()
                            .filter(pf -> "Jeff".equals(pf.name()))
                            .map(PlayerFrames::frames)
                            .flatMap(Collection::stream)
                            .map(Frame::score)
                            .max(Comparator.naturalOrder()).orElse(0), 167);
            assertEquals(
                    list.stream()
                            .filter(pf -> "John".equals(pf.name()))
                            .map(PlayerFrames::frames)
                            .flatMap(Collection::stream)
                            .map(Frame::score)
                            .max(Comparator.naturalOrder()).orElse(0), 151);
            return true;
        }));
        verifyNoMoreInteractions(printer);

    }

    @Test
    void testGenerateScoreForPerfectScore() throws Exception {
        var parser = mock(Parser.class);
        var printer = mock(Printer.class);
        var readable = mock(Readable.class);

        given(parser.parse(readable))
                .willReturn(parsePerfectScoreResult());

        GameScorer gs = new GameScorer(parser, printer);
        gs.generateScore(readable);

        then(parser).should(only()).parse(readable);
        verifyNoMoreInteractions(parser);

        then(printer).should(only()).print(anyList());
        verify(printer).print(argThat(list -> {
            assertIterableEquals(expectedPerfectScorePlayerFrames(), list);
            return true;
        }));
        verifyNoMoreInteractions(printer);

    }

    @Test
    void testGenerateScoreForZeroScore() throws Exception {
        var parser = mock(Parser.class);
        var printer = mock(Printer.class);
        var readable = mock(Readable.class);

        given(parser.parse(readable))
                .willReturn(parseZeroScoreResult());

        GameScorer gs = new GameScorer(parser, printer);
        gs.generateScore(readable);

        then(parser).should(only()).parse(readable);
        verifyNoMoreInteractions(parser);

        then(printer).should(only()).print(anyList());
        verify(printer).print(argThat(list -> {
            assertIterableEquals(expectedPZeroScorePlayerFrames(), list);
            return true;
        }));
        verifyNoMoreInteractions(printer);

    }

    private Set<PlayerRolls> parsePerfectScoreResult() {
        var playerRolls = new PlayerRolls(
                "Carl",
                nCopies(12, "10"));
        var allPlayerRolls = new LinkedHashSet<PlayerRolls>(1);
        allPlayerRolls.add(playerRolls);
        return allPlayerRolls;
    }

    private List<PlayerFrames> expectedPerfectScorePlayerFrames() {
        var frames = new ArrayList<Frame>(10);
        for (int i = 0; i < 9; i++) {
            var f = new Frame(singletonList("10"), (i + 1) * 30);
            frames.add(f);
        }
        var tenthF = new Frame(asList("10", "10", "10"), 300);
        frames.add(tenthF);
        PlayerFrames pf = new PlayerFrames("Carl", frames);
        return singletonList(pf);
    }

    private Set<PlayerRolls> parseZeroScoreResult() {
        var playerRolls = new PlayerRolls(
                "Carl",
                nCopies(20, "0"));
        var allPlayerRolls = new LinkedHashSet<PlayerRolls>(1);
        allPlayerRolls.add(playerRolls);
        return allPlayerRolls;
    }

    private List<PlayerFrames> expectedPZeroScorePlayerFrames() {
        var frames = new ArrayList<Frame>(10);
        for (int i = 0; i < 10; i++) {
            var f = new Frame(asList("0", "0"), 0);
            frames.add(f);
        }
        PlayerFrames pf = new PlayerFrames("Carl", frames);
        return singletonList(pf);
    }
}