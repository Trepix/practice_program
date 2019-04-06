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

    @Test
    public void turns_left_when_pointing_north_using_nasa_commands() {
        MarsRover marsRover = aMultiInterpreterMarsRoverAnywhere().facing("N").build();

        marsRover.receive("zl");

        assertThat(marsRover, is(aMultiInterpreterMarsRoverAnywhere().facing("W").build()));
    }

    @Test
    public void turns_left_when_pointing_west_using_nasa_commands() {
        MarsRover marsRover = aMultiInterpreterMarsRoverAnywhere().facing("W").build();

        marsRover.receive("zl");

        assertThat(marsRover, is(aMultiInterpreterMarsRoverAnywhere().facing("S").build()));
    }

    @Test
    public void turns_left_when_pointing_south_using_nasa_commands() {
        MarsRover marsRover = aMultiInterpreterMarsRoverAnywhere().facing("S").build();

        marsRover.receive("zl");

        assertThat(marsRover, is(aMultiInterpreterMarsRoverAnywhere().facing("E").build()));
    }

    @Test
    public void turns_left_when_pointing_east_using_nasa_commands() {
        MarsRover marsRover = aMultiInterpreterMarsRoverAnywhere().facing("E").build();

        marsRover.receive("zl");

        assertThat(marsRover, is(aMultiInterpreterMarsRoverAnywhere().facing("N").build()));
    }

    @Test
    public void moves_forward_when_pointing_north_using_nasa_commands() {
        MarsRover marsRover = aMultiInterpreterMarsRover().at(5, 4).facing("N").build();

        marsRover.receive("zf");

        assertThat(marsRover, is(aMultiInterpreterMarsRover().at(5, 5).facing("N").build()));
    }

    @Test
    public void moves_forward_when_pointing_east_using_nasa_commands() {
        MarsRover marsRover = aMultiInterpreterMarsRover().at(5, 4).facing("E").build();

        marsRover.receive("zf");

        assertThat(marsRover, is(aMultiInterpreterMarsRover().at(6, 4).facing("E").build()));
    }

    @Test
    public void moves_forward_when_pointing_south_using_nasa_commands() {
        MarsRover marsRover = aMultiInterpreterMarsRover().at(5, 4).facing("S").build();

        marsRover.receive("zf");

        assertThat(marsRover, is(aMultiInterpreterMarsRover().at(5, 3).facing("S").build()));
    }

    @Test
    public void moves_forward_when_pointing_west_using_nasa_commands() {
        MarsRover marsRover = aMultiInterpreterMarsRover().at(5, 4).facing("W").build();

        marsRover.receive("zf");

        assertThat(marsRover, is(aMultiInterpreterMarsRover().at(4, 4).facing("W").build()));
    }

    @Test
    public void moves_backward_when_pointing_north_using_nasa_commands() {
        MarsRover marsRover = aMultiInterpreterMarsRover().at(5, 4).facing("N").build();

        marsRover.receive("zb");

        assertThat(marsRover, is(aMultiInterpreterMarsRover().at(5, 3).facing("N").build()));
    }

    @Test
    public void moves_backward_when_pointing_east_using_nasa_commands() {
        MarsRover marsRover = aMultiInterpreterMarsRover().at(5, 4).facing("E").build();

        marsRover.receive("zb");

        assertThat(marsRover, is(aMultiInterpreterMarsRover().at(4, 4).facing("E").build()));
    }

    @Test
    public void moves_backward_when_pointing_south_using_nasa_commands() {
        MarsRover marsRover = aMultiInterpreterMarsRover().at(5, 4).facing("S").build();

        marsRover.receive("zb");

        assertThat(marsRover, is(aMultiInterpreterMarsRover().at(5, 5).facing("S").build()));
    }

    @Test
    public void moves_backward_when_pointing_west_using_nasa_commands() {
        MarsRover marsRover = aMultiInterpreterMarsRover().at(5, 4).facing("W").build();

        marsRover.receive("zb");

        assertThat(marsRover, is(aMultiInterpreterMarsRover().at(6, 4).facing("W").build()));
    }

    @Test
    public void receives_multiple_commands_using_nasa_commands() {
        MarsRover marsRover = aMultiInterpreterMarsRover().at(7, 4).facing("E").build();

        marsRover.receive("zfr");

        assertThat(marsRover, is(aMultiInterpreterMarsRover().at(8, 4).facing("S").build()));
    }

    @Test
    public void turns_right_when_pointing_north_using_esa_commands() {
        MarsRover marsRover = aMultiInterpreterMarsRoverAnywhere().facing("N").build();

        marsRover.receive("kl");

        assertThat(marsRover, is(aMultiInterpreterMarsRoverAnywhere().facing("E").build()));
    }

    @Test
    public void turns_right_when_pointing_east_using_esa_commands() {
        MarsRover marsRover = aMultiInterpreterMarsRoverAnywhere().facing("E").build();

        marsRover.receive("kl");

        assertThat(marsRover, is(aMultiInterpreterMarsRoverAnywhere().facing("S").build()));
    }

    @Test
    public void turns_right_when_pointing_south_using_esa_commands() {
        MarsRover marsRover = aMultiInterpreterMarsRoverAnywhere().facing("S").build();

        marsRover.receive("kl");

        assertThat(marsRover, is(aMultiInterpreterMarsRoverAnywhere().facing("W").build()));
    }

    @Test
    public void turns_right_when_pointing_west_using_esa_commands() {
        MarsRover marsRover = aMultiInterpreterMarsRoverAnywhere().facing("W").build();

        marsRover.receive("kl");

        assertThat(marsRover, is(aMultiInterpreterMarsRoverAnywhere().facing("N").build()));
    }

}
