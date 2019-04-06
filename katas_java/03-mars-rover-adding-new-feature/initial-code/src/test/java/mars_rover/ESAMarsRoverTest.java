package mars_rover;

import org.junit.Test;

import static mars_rover.MarsRoverBuilder.*;
import static mars_rover.MarsRoverBuilder.anESAMarsRover;
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

    @Test
    public void turns_left_when_pointing_north() {
        MarsRover marsRover = anESAMarsRoverAnywhere().facing("N").build();

        marsRover.receive("f");

        assertThat(marsRover, is(anESAMarsRoverAnywhere().facing("W").build()));
    }

    @Test
    public void turns_left_when_pointing_west() {
        MarsRover marsRover = anESAMarsRoverAnywhere().facing("W").build();

        marsRover.receive("f");

        assertThat(marsRover, is(anESAMarsRoverAnywhere().facing("S").build()));
    }

    @Test
    public void turns_left_when_pointing_south() {
        MarsRover marsRover = anESAMarsRoverAnywhere().facing("S").build();

        marsRover.receive("f");

        assertThat(marsRover, is(anESAMarsRoverAnywhere().facing("E").build()));
    }

    @Test
    public void turns_left_when_pointing_east() {
        MarsRover marsRover = anESAMarsRoverAnywhere().facing("E").build();

        marsRover.receive("f");

        assertThat(marsRover, is(anESAMarsRoverAnywhere().facing("N").build()));
    }

    @Test
    public void moves_forward_when_pointing_north() {
        MarsRover marsRover = anESAMarsRover().at(5, 4).facing("N").build();

        marsRover.receive("b");

        assertThat(marsRover, is(anESAMarsRover().at(5, 5).facing("N").build()));
    }

    @Test
    public void moves_forward_when_pointing_east() {
        MarsRover marsRover = anESAMarsRover().at(5, 4).facing("E").build();

        marsRover.receive("b");

        assertThat(marsRover, is(anESAMarsRover().at(6, 4).facing("E").build()));
    }

    @Test
    public void moves_forward_when_pointing_south() {
        MarsRover marsRover = anESAMarsRover().at(5, 4).facing("S").build();

        marsRover.receive("b");

        assertThat(marsRover, is(anESAMarsRover().at(5, 3).facing("S").build()));
    }

    @Test
    public void moves_forward_when_pointing_west() {
        MarsRover marsRover = anESAMarsRover().at(5, 4).facing("W").build();

        marsRover.receive("b");

        assertThat(marsRover, is(anESAMarsRover().at(4, 4).facing("W").build()));
    }

    @Test
    public void moves_backward_when_pointing_north() {
        MarsRover marsRover = anESAMarsRover().at(5, 4).facing("N").build();

        marsRover.receive("x");

        assertThat(marsRover, is(anESAMarsRover().at(5, 3).facing("N").build()));
    }

    @Test
    public void moves_backward_when_pointing_east() {
        MarsRover marsRover = anESAMarsRover().at(5, 4).facing("E").build();

        marsRover.receive("x");

        assertThat(marsRover, is(anESAMarsRover().at(4, 4).facing("E").build()));
    }

    @Test
    public void moves_backward_when_pointing_south() {
        MarsRover marsRover = anESAMarsRover().at(5, 4).facing("S").build();

        marsRover.receive("x");

        assertThat(marsRover, is(anESAMarsRover().at(5, 5).facing("S").build()));
    }

    @Test
    public void moves_backward_when_pointing_west() {
        MarsRover marsRover = anESAMarsRover().at(5, 4).facing("W").build();

        marsRover.receive("x");

        assertThat(marsRover, is(anESAMarsRover().at(6, 4).facing("W").build()));
    }

    @Test
    public void receives_multiple_commands() {
        MarsRover marsRover = anESAMarsRover().at(7, 4).facing("E").build();

        marsRover.receive("bl");

        assertThat(marsRover, is(anESAMarsRover().at(8, 4).facing("S").build()));
    }
}
