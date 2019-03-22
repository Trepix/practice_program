package com.codesai.marsrover.movement;

import org.junit.jupiter.api.Test;

import static com.codesai.marsrover.movement.CardinalDirection.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class RoverDisplacementTests {

    @Test
    void lookingToNorthMoveForward() {
        Rover rover = createRoverAtOriginLookingTo(NORTH);

        rover.process("f");

        assertThat(rover, is(new Rover(NORTH, new Position(0, 1))));
    }

    @Test
    void lookingToNorthMoveBackward() {
        Rover rover = createRoverAtOriginLookingTo(NORTH);

        rover.process("b");

        assertThat(rover, is(new Rover(NORTH, new Position(0, -1))));
    }

    @Test
    void lookingToEastMoveForward() {
        Rover rover = createRoverAtOriginLookingTo(EAST);

        rover.process("f");

        assertThat(rover, is(new Rover(EAST, new Position(1, 0))));
    }


    @Test
    void lookingToEastMoveBackward() {
        Rover rover = createRoverAtOriginLookingTo(EAST);

        rover.process("b");

        assertThat(rover, is(new Rover(EAST, new Position(-1, 0))));
    }

    @Test
    void lookingToSouthMoveForward() {
        Rover rover = createRoverAtOriginLookingTo(SOUTH);

        rover.process("f");

        assertThat(rover, is(new Rover(SOUTH, new Position(0, -1))));
    }

    @Test
    void lookingToSouthMoveBackward() {
        Rover rover = createRoverAtOriginLookingTo(SOUTH);

        rover.process("b");

        assertThat(rover, is(new Rover(SOUTH, new Position(0, 1))));
    }

    private Rover createRoverAtOriginLookingTo(CardinalDirection cardinalDirection) {
        return new Rover(cardinalDirection, originPosition());
    }

    private Position originPosition() {
        return new Position(0, 0);
    }
}
