package ohce;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class OcheTest {

    @Test
    public void runs_when_is_in_the_morning_receiving_palindrome_and_non_palindrome_words() {
        IO io = mock(IO.class);
        Reader reader = mock(Reader.class);
        Hour hour = new StubHour(10);
        Oche oche = new Oche(io, reader, hour);

        when(reader.nextWord()).thenReturn("hola", "oto", "stop", "Stop!");

        oche.runs("Karl");

        verify(io).write("¡Buenas días Karl!");
        verify(io).write("aloh");
        verify(io).write("oto");
        verify(io).write("¡Bonita palabra!");
        verify(io).write("pots");
        verify(io).write("Adios Karl");
    }


    @Test
    public void runs_when_is_in_the_afternoon_receiving_palindrome_and_non_palindrome_words() {
        IO io = mock(IO.class);
        Hour hour = new StubHour(18);
        Oche oche = new Oche(io, hour);
        when(io.read()).thenReturn("hola", "abba", "Stop!");

        oche.runs("Karl");

        verify(io).write("¡Buenas tardes Karl!");
        verify(io).write("aloh");
        verify(io).write("abba");
        verify(io).write("¡Bonita palabra!");
        verify(io).write("Adios Karl");
    }

    @Test
    public void runs_when_is_in_the_night_receiving_palindrome_and_non_palindrome_words() {
        IO io = mock(IO.class);
        Hour hour = new StubHour(22);
        Oche oche = new Oche(io, hour);
        when(io.read()).thenReturn("hello", "alla", "Stop!");

        oche.runs("Karl");

        verify(io).write("¡Buenas noches Karl!");
        verify(io).write("olleh");
        verify(io).write("alla");
        verify(io).write("¡Bonita palabra!");
        verify(io).write("Adios Karl");
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