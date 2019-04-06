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
    public void does_nothing_when_commands_interpreter_is_not_specified() {
        MarsRover marsRover = anyMultiInterpreterMarsRover().build();

        marsRover.receive("rlbx");

        assertThat(marsRover, is(anyMultiInterpreterMarsRover().build()));
    }

    @Test
    public void turns_right_when_pointing_north_using_nasa_commands() {
        MarsRover marsRover = aMultiInterpreterMarsRoverAnywhere().facing("N").build();

        marsRover.receive("zr");

        assertThat(marsRover, is(aMultiInterpreterMarsRoverAnywhere().facing("E").build()));
    }
    @Test
    public void turns_right_when_pointing_east_using_nasa_commands() {
        MarsRover marsRover = aMultiInterpreterMarsRoverAnywhere().facing("E").build();

        marsRover.receive("zr");

        assertThat(marsRover, is(aMultiInterpreterMarsRoverAnywhere().facing("S").build()));
    }

    @Test
    public void turns_right_when_pointing_south_using_nasa_commands() {
        MarsRover marsRover = aMultiInterpreterMarsRoverAnywhere().facing("S").build();

        marsRover.receive("zr");

        assertThat(marsRover, is(aMultiInterpreterMarsRoverAnywhere().facing("W").build()));
    }

    @Test
    public void turns_right_when_pointing_west_using_nasa_commands() {
        MarsRover marsRover = aMultiInterpreterMarsRoverAnywhere().facing("W").build();

        marsRover.receive("zr");

        assertThat(marsRover, is(aMultiInterpreterMarsRoverAnywhere().facing("N").build()));
    }

}
