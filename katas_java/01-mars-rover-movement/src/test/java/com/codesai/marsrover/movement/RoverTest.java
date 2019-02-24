package com.codesai.marsrover.movement;

import org.junit.jupiter.api.Test;

import static com.codesai.marsrover.movement.CardinalDirection.NORTH;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class RoverTest {

    @Test
    void givenEmptyString_ShouldNotMoveRover() {
        Rover rover = new Rover(NORTH, originPosition());

        rover.process();

        Rover sameStateRover = new Rover(NORTH, originPosition());
        assertThat(rover, is(sameStateRover));
    }

    private Position originPosition() {
        return new Position(0, 0);
    }
}
