package com.codesai.marsrover.movement;

import org.junit.jupiter.api.Test;

import static com.codesai.marsrover.movement.CardinalDirection.EAST;
import static com.codesai.marsrover.movement.CardinalDirection.NORTH;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class RoverRotationTests {

    @Test
    void lookingToNorthRotateRight() {
        Rover rover = createRoverLookingTo(NORTH);

        rover.process("r");

        Rover lookingEastRover = createRoverLookingTo(EAST);
        assertThat(rover, is(lookingEastRover));
    }

    private Rover createRoverLookingTo(CardinalDirection cardinalDirection) {
        return new Rover(cardinalDirection, originPosition());
    }

    private Position originPosition() {
        return new Position(0, 0);
    }
}
