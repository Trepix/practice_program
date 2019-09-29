package ohce;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class OcheTest {

    @Test
    public void runs_when_is_in_the_morning_receiving_palindrome_and_non_palindrome_words() {
        IO io = mock(IO.class);
        Oche oche = new Oche(io);
        when(io.read()).thenReturn("hola", "oto", "stop", "Stop!");

        oche.runs("Karl");

        verify(io).write("¡Buenas días Karl!");
        verify(io).write("aloh");
        verify(io).write("oto");
        verify(io).write("¡Bonita palabra!");
        verify(io).write("pots");
        verify(io).write("Adios Karl");
    }
}