package com.codesai.marsrover.movement;

import org.junit.jupiter.api.Test;

import static com.codesai.marsrover.movement.CardinalDirection.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class RoverCommandsProcessingTests {

    @Test
    void givenEmptyString_ShouldNotMoveRover() {
        Rover rover = new Rover(NORTH, originPosition());

        rover.process("");

        Rover sameStateRover = new Rover(NORTH, originPosition());
        assertThat(rover, is(sameStateRover));
    }

    @Test
    void givenTwoCommands_ShouldMove() {
        Rover rover = new Rover(NORTH, originPosition());

        rover.process("rf");

        assertThat(rover, is(new Rover(EAST, new Position(1, 0))));
    }

    @Test
    void givenMultipleCommands_ShouldMove() {
        Rover rover = new Rover(NORTH, originPosition());

        rover.process("ffrffrb");

        assertThat(rover, is(new Rover(SOUTH, new Position(2, 3))));
    }

    private Position originPosition() {
        return new Position(0, 0);
    }
}
