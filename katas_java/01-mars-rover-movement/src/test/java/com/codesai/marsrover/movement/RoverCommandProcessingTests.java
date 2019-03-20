package com.codesai.marsrover.movement;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codesai.marsrover.movement.CardinalDirection.*;
import static com.codesai.marsrover.movement.RoverCreationUtils.createRoverLookingTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class RoverCommandProcessingTests {

    @Test
    @DisplayName("Given empty string commands should not move")
    void shouldNotMoveRover() {
        Rover rover = createRoverLookingTo(NORTH);

        rover.process("");

        Rover sameStateRover = createRoverLookingTo(NORTH);
        assertThat(rover, is(sameStateRover));
    }

    @Test
    @DisplayName("Given multiple commands should be able to process it and move")
    void shouldProcessMultipleCommandsFromOrigin() {
        Rover rover = createRoverLookingTo(NORTH);

        rover.process("ffrffrb");

        Rover movedRover = new Rover(SOUTH, new Position(2, 3));
        assertThat(rover, is(movedRover));
    }

    @Test
    @DisplayName("Given multiple commands when starting from non origin position should be able to process it and move")
    void shouldProcessMultipleCommandsFromNotOrigin() {
        Rover rover = new Rover(EAST, new Position(4, 5));

        rover.process("lbrf");

        Rover movedRover = new Rover(EAST, new Position(5, 4));
        assertThat(rover, is(movedRover));
    }

    @Test
    @Disabled
    @DisplayName("Given invalid command we don't know what should do")
    void givenInvalidCommandCase() {
        Rover rover = new Rover(EAST, new Position(4, 5));

        rover.process("x");
    }


}
