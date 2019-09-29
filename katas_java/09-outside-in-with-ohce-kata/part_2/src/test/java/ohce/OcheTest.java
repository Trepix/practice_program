package ohce;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class OcheTest {

    @Test
    public void runs_when_is_in_the_morning_receiving_palindrome_and_non_palindrome_words() {
        IO io = mock(IO.class);
        Reader reader = mock(Reader.class);
        Writer writer= mock(Writer.class);
        Hour hour = new StubHour(10);
        Oche oche = new Oche(io, reader, writer, hour);

        when(reader.nextWord()).thenReturn("hola", "oto", "stop", "Stop!");

        oche.runs("Karl");

        verify(writer).write("¡Buenas días Karl!");
        verify(writer).write("aloh");
        verify(writer).write("oto");
        verify(writer).write("¡Bonita palabra!");
        verify(writer).write("pots");
        verify(writer).write("Adios Karl");
    }


    @Test
    public void runs_when_is_in_the_afternoon_receiving_palindrome_and_non_palindrome_words() {
        IO io = mock(IO.class);
        Reader reader = mock(Reader.class);
        Writer writer= mock(Writer.class);
        Hour hour = new StubHour(18);
        Oche oche = new Oche(io, reader, writer, hour);
        when(reader.nextWord()).thenReturn("hola", "abba", "Stop!");

        oche.runs("Karl");

        verify(writer).write("¡Buenas tardes Karl!");
        verify(writer).write("aloh");
        verify(writer).write("abba");
        verify(writer).write("¡Bonita palabra!");
        verify(writer).write("Adios Karl");
    }

    @Test
    public void runs_when_is_in_the_night_receiving_palindrome_and_non_palindrome_words() {
        IO io = mock(IO.class);
        Reader reader = mock(Reader.class);
        Writer writer= mock(Writer.class);
        Hour hour = new StubHour(22);
        Oche oche = new Oche(io, reader, writer, hour);
        when(reader.nextWord()).thenReturn("hello", "alla", "Stop!");

        oche.runs("Karl");

        verify(writer).write("¡Buenas noches Karl!");
        verify(writer).write("olleh");
        verify(writer).write("alla");
        verify(writer).write("¡Bonita palabra!");
        verify(writer).write("Adios Karl");
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