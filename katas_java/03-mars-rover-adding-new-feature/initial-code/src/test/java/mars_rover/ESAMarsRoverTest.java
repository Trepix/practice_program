package mars_rover;

import org.junit.Test;

import static mars_rover.MarsRoverBuilder.anESAMarsRoverAnywhere;
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

    @Test
    public void turns_right_when_pointing_north() {
        MarsRover marsRover = anESAMarsRoverAnywhere().facing("N").build();

        marsRover.receive("l");

        assertThat(marsRover, is(anESAMarsRoverAnywhere().facing("E").build()));
    }

    @Test
    public void turns_right_when_pointing_east() {
        MarsRover marsRover = anESAMarsRoverAnywhere().facing("E").build();

        marsRover.receive("l");

        assertThat(marsRover, is(anESAMarsRoverAnywhere().facing("S").build()));
    }

    @Test
    public void turns_right_when_pointing_south() {
        MarsRover marsRover = anESAMarsRoverAnywhere().facing("S").build();

        marsRover.receive("l");

        assertThat(marsRover, is(anESAMarsRoverAnywhere().facing("W").build()));
    }

    @Test
    public void turns_right_when_pointing_west() {
        MarsRover marsRover = anESAMarsRoverAnywhere().facing("W").build();

        marsRover.receive("l");

        assertThat(marsRover, is(anESAMarsRoverAnywhere().facing("N").build()));
    }
}
