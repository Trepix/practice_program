package com.codesai.marsrover.movement;

import org.junit.jupiter.api.Test;

import static com.codesai.marsrover.movement.CardinalDirection.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class RoverRotationTests {

    @Test
    void lookingToNorthRotateRight() {
        Rover rover = createRoverLookingTo(NORTH);

        rover.process("r");

        assertThat(rover, is(createRoverLookingTo(EAST)));
    }

    @Test
    void lookingToNorthRotateLeft() {
        Rover rover = createRoverLookingTo(NORTH);

        rover.process("l");

        assertThat(rover, is(createRoverLookingTo(WEST)));
    }

    @Test
    void lookingToEastRotateRight() {
        Rover rover = createRoverLookingTo(EAST);

        rover.process("r");

        assertThat(rover, is(createRoverLookingTo(SOUTH)));
    }

    @Test
    void lookingToEastRotateLeft() {
        Rover rover = createRoverLookingTo(EAST);

        rover.process("l");

        assertThat(rover, is(createRoverLookingTo(NORTH)));
    }

    @Test
    void lookingToSouthRotateRight() {
        Rover rover = createRoverLookingTo(SOUTH);

        rover.process("r");

        assertThat(rover, is(createRoverLookingTo(WEST)));
    }

    @Test
    void lookingToSouthRotateLeft() {
        Rover rover = createRoverLookingTo(SOUTH);

        rover.process("l");

        assertThat(rover, is(createRoverLookingTo(EAST)));
    }

    @Test
    void lookingToWestRotateRight() {
        Rover rover = createRoverLookingTo(WEST);

        rover.process("r");

        assertThat(rover, is(createRoverLookingTo(NORTH)));
    }

    @Test
    void lookingToWestRotateLeft() {
        Rover rover = createRoverLookingTo(WEST);

        rover.process("l");

        assertThat(rover, is(createRoverLookingTo(SOUTH)));
    }


    private Rover createRoverLookingTo(CardinalDirection cardinalDirection) {
        return new Rover(cardinalDirection, originPosition());
    }

    private Position originPosition() {
        return new Position(0, 0);
    }
}
