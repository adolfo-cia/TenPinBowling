package challenge.print;

import challenge.model.Frame;
import challenge.model.PlayerFrames;
import org.junit.jupiter.api.Test;

import static java.util.Collections.singletonList;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoMoreInteractions;

class ConsolePrinterTest {

    @Test
    void testPrint() {
        var frame = mock(Frame.class);
        given(frame.rolls()).willReturn(singletonList("10"));
        given(frame.score()).willReturn(10);
        var pf = mock(PlayerFrames.class);
        given(pf.frames()).willReturn(singletonList(frame));
        given(pf.name()).willReturn("Carl");
        var allPlayerFrames = singletonList(pf);

        var printer = new ConsolePrinter();
        printer.print(allPlayerFrames);

        then(pf).should(times(1)).name();
        then(pf).should(times(1)).frames();
        then(frame).should(times(1)).rolls();
        then(frame).should(times(0)).sumFirstTwoRolls();
        then(frame).should(times(1)).score();
        verifyNoMoreInteractions(pf);
        verifyNoMoreInteractions(frame);

    }
}