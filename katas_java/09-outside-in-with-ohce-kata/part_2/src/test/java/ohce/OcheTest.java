package ohce;

import org.junit.Test;

import java.util.stream.Stream;

import static org.mockito.Mockito.*;

public class OcheTest {

    @Test
    public void runs_when_is_in_the_first_hour_of_the_morning_receiving_palindrome_and_non_palindrome_words() {
        Reader reader = mock(Reader.class);
        Writer writer = mock(Writer.class);
        Hour hour = new StubHour(6);
        Oche oche = new Oche(reader, writer, hour);
        when(reader.nextWord()).thenReturn("hola", "oto", "stop", "Stop!");

        oche.runs("Karl");

        verifyThatHasBeenWritten(writer,
                "¡Buenas días Karl!",
                "aloh",
                "oto",
                "¡Bonita palabra!",
                "pots",
                "Adios Karl");
    }

    @Test
    public void runs_when_is_in_the_last_hour_of_the_morning_receiving_palindrome_and_non_palindrome_words() {
        Reader reader = mock(Reader.class);
        Writer writer = mock(Writer.class);
        Hour hour = new StubHour(11);
        Oche oche = new Oche(reader, writer, hour);
        when(reader.nextWord()).thenReturn("hola", "oto", "stop", "Stop!");

        oche.runs("Karl");

        verifyThatHasBeenWritten(writer,
                "¡Buenas días Karl!",
                "aloh",
                "oto",
                "¡Bonita palabra!",
                "pots",
                "Adios Karl");
    }


    @Test
    public void runs_when_is_in_the_afternoon_receiving_palindrome_and_non_palindrome_words() {
        Reader reader = mock(Reader.class);
        Writer writer = mock(Writer.class);
        Hour hour = new StubHour(18);
        Oche oche = new Oche(reader, writer, hour);
        when(reader.nextWord()).thenReturn("hola", "abba", "Stop!");

        oche.runs("Karl");

        verifyThatHasBeenWritten(writer,
                "¡Buenas tardes Karl!",
                "aloh",
                "abba",
                "¡Bonita palabra!",
                "Adios Karl");
    }

    @Test
    public void runs_when_is_in_the_first_hour_of_the_afternoon_receiving_palindrome_and_non_palindrome_words() {
        Reader reader = mock(Reader.class);
        Writer writer = mock(Writer.class);
        Hour hour = new StubHour(12);
        Oche oche = new Oche(reader, writer, hour);
        when(reader.nextWord()).thenReturn("hola", "abba", "Stop!");

        oche.runs("Karl");

        verifyThatHasBeenWritten(writer,
                "¡Buenas tardes Karl!",
                "aloh",
                "abba",
                "¡Bonita palabra!",
                "Adios Karl");
    }


    @Test
    public void runs_when_is_in_the_night_receiving_palindrome_and_non_palindrome_words() {
        Reader reader = mock(Reader.class);
        Writer writer = mock(Writer.class);
        Hour hour = new StubHour(22);
        Oche oche = new Oche(reader, writer, hour);
        when(reader.nextWord()).thenReturn("hello", "alla", "Stop!");

        oche.runs("Karl");

        verifyThatHasBeenWritten(writer,
                "¡Buenas noches Karl!",
                "olleh",
                "alla",
                "¡Bonita palabra!",
                "Adios Karl");
    }

    private static void verifyThatHasBeenWritten(Writer writer, String... lines) {
        Stream.of(lines).forEach(line -> verify(writer).write(line));
    }

    private class StubHour extends Hour {

        private final int value;

        private StubHour(int value) {
            this.value = value;
        }

        @Override
        int getIn24Format() {
            return this.value;
        }
    }
}