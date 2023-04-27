import org.example.Main;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {
    private final PrintStream standardOut = System.out;
    private final InputStream standardIn = System.in;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void reset() {
        System.setOut(standardOut);
        System.setIn(standardIn);
        outputStreamCaptor.reset();
    }

    @ParameterizedTest
    @MethodSource("testArguments")
    void successTest(String input, String expectedRating) {
        final InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Main.main(new String[0]);

        if (expectedRating != null && !expectedRating.equals("")) {
            assertTrue(outputStreamCaptor.toString().contains(expectedRating));
        }
        else {
            assertFalse(outputStreamCaptor.toString().contains("Die Bewertung für den Film ist:"));
        }
    }

    private static Stream<Arguments> testArguments() {
        return Stream.of(
            Arguments.of("1", "3,92"),
            Arguments.of("555", "3,80"),
            Arguments.of("1001", ""),
            Arguments.of("90603", "3,67"),
            Arguments.of("190221", "1,00"),
            Arguments.of("keiner", "")
        );
    }
}
