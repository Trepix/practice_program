package mars_rover;

import org.junit.Test;

import static mars_rover.MarsRoverBuilder.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MarsRoverTest {

    @Test
    public void does_nothing_when_receiving_empty_commands_sequence() {
        MarsRover marsRover = anyMarsRover().build();

        marsRover.receive("");

        assertThat(marsRover, is(anyMarsRover().build()));
    }

    @Test
    public void turns_right_when_pointing_north() {
        MarsRover marsRover = aMarsRover().at(0, 0).facing("N").build();

        marsRover.receive("r");

        assertThat(marsRover, is(aMarsRover().at(0, 0).facing("E").build()));
    }

    @Test
    public void turns_right_when_pointing_east() {
        MarsRover marsRover = aMarsRover().at(0, 0).facing("E").build();

        marsRover.receive("r");

        assertThat(marsRover, is(aMarsRover().at(0, 0).facing("S").build()));
    }

    @Test
    public void turns_right_when_pointing_south() {
        MarsRover marsRover = aMarsRover().at(0, 0).facing("S").build();

        marsRover.receive("r");

        assertThat(marsRover, is(aMarsRover().at(0, 0).facing("W").build()));
    }

    @Test
    public void turns_right_when_pointing_west() {
        MarsRover marsRover = aMarsRover().at(0, 0).facing("W").build();

        marsRover.receive("r");

        assertThat(marsRover, is(aMarsRover().at(0, 0).facing("N").build()));
    }

    @Test
    public void turns_left_when_pointing_north() {
        MarsRover marsRover = aMarsRover().at(0, 0).facing("N").build();

        marsRover.receive("l");

        assertThat(marsRover, is(aMarsRover().at(0, 0).facing("W").build()));
    }

    @Test
    public void turns_left_when_pointing_west() {
        MarsRover marsRover = aMarsRover().at(0, 0).facing("W").build();

        marsRover.receive("l");

        assertThat(marsRover, is(aMarsRover().at(0, 0).facing("S").build()));
    }

    @Test
    public void turns_left_when_pointing_south() {
        MarsRover marsRover = aMarsRover().at(0, 0).facing("S").build();

        marsRover.receive("l");

        assertThat(marsRover, is(aMarsRover().at(0, 0).facing("E").build()));
    }

    @Test
    public void turns_left_when_pointing_east() {
        MarsRover marsRover = aMarsRover().at(0, 0).facing("E").build();

        marsRover.receive("l");

        assertThat(marsRover, is(aMarsRover().at(0, 0).facing("N").build()));
    }

    @Test
    public void moves_forward_when_pointing_north() {
        MarsRover marsRover = aMarsRover().at(5, 4).facing("N").build();

        marsRover.receive("f");

        assertThat(marsRover, is(aMarsRover().at(5, 5).facing("N").build()));
    }

    @Test
    public void moves_forward_when_pointing_east() {
        MarsRover marsRover = aMarsRover().at(5, 4).facing("E").build();

        marsRover.receive("f");

        assertThat(marsRover, is(aMarsRover().at(6, 4).facing("E").build()));
    }

    @Test
    public void moves_forward_when_pointing_south() {
        MarsRover marsRover = aMarsRover().at(5, 4).facing("S").build();

        marsRover.receive("f");

        assertThat(marsRover, is(aMarsRover().at(5, 3).facing("S").build()));
    }

    @Test
    public void moves_forward_when_pointing_west() {
        MarsRover marsRover = aMarsRover().at(5, 4).facing("W").build();

        marsRover.receive("f");

        assertThat(marsRover, is(aMarsRover().at(4, 4).facing("W").build()));
    }

    @Test
    public void moves_backward_when_pointing_north() {
        MarsRover marsRover = aMarsRover().at(5, 4).facing("N").build();

        marsRover.receive("b");

        assertThat(marsRover, is(aMarsRover().at(5, 3).facing("N").build()));
    }

    @Test
    public void moves_backward_when_pointing_east() {
        MarsRover marsRover = aMarsRover().at(5, 4).facing("E").build();

        marsRover.receive("b");

        assertThat(marsRover, is(aMarsRover().at(4, 4).facing("E").build()));
    }

    @Test
    public void moves_backward_when_pointing_south() {
        MarsRover marsRover = aMarsRover().at(5, 4).facing("S").build();

        marsRover.receive("b");

        assertThat(marsRover, is(aMarsRover().at(5, 5).facing("S").build()));
    }

    @Test
    public void moves_backward_when_pointing_west() {
        MarsRover marsRover = aMarsRover().at(5, 4).facing("W").build();

        marsRover.receive("b");

        assertThat(marsRover, is(aMarsRover().at(6, 4).facing("W").build()));
    }

    @Test
    public void receives_multiple_commands() {
        MarsRover marsRover = aMarsRover().at(7, 4).facing("E").build();

        marsRover.receive("fr");

        assertThat(marsRover, is(aMarsRover().at(8, 4).facing("S").build()));
    }

    @Test
    public void ignores_unknown_commands() {
        MarsRover marsRover = aMarsRover().at(7, 4).facing("E").build();

        marsRover.receive("*");

        assertThat(marsRover, is(aMarsRover().at(7, 4).facing("E").build()));
    }

}
