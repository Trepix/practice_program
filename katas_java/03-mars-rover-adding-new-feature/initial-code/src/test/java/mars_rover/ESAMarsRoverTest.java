package mars_rover;

import org.junit.Test;

import static mars_rover.MarsRoverBuilder.anyESAMarsRover;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ESAMarsRoverTest {

    @Test
    public void does_nothing_when_receiving_empty_commands_sequence() {
        MarsRover marsRover = anyESAMarsRover().build();

        marsRover.receive("");

        assertThat(marsRover, is(anyESAMarsRover().build()));
    }
}
