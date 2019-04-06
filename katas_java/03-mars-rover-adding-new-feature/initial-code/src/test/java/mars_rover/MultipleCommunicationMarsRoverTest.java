package mars_rover;

import org.junit.Test;

import static mars_rover.MarsRoverBuilder.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MultipleCommunicationMarsRoverTest {

    @Test
    public void does_nothing_when_receiving_empty_commands_sequence() {
        MarsRover marsRover = anyMultiInterpreterMarsRover().build();

        marsRover.receive("");

        assertThat(marsRover, is(anyMultiInterpreterMarsRover().build()));
    }

    @Test
    public void does_nothing_when_communication_interpreter_is_not_specified() {
        MarsRover marsRover = anyMultiInterpreterMarsRover().build();

        marsRover.receive("rlbx");

        assertThat(marsRover, is(anyMultiInterpreterMarsRover().build()));
    }

}
