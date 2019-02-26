package com.codesai.marsrover.movement;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.codesai.marsrover.movement.CardinalDirection.EAST;
import static com.codesai.marsrover.movement.CardinalDirection.NORTH;
import static com.codesai.marsrover.movement.CardinalDirection.WEST;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class RoverTest {

    @Test
    @DisplayName("Given empty string commands should not move")
    void shouldNotMoveRover() {
        Rover rover = createRoverLookingTo(NORTH);

        rover.process();

        Rover sameStateRover = createRoverLookingTo(NORTH);
        assertThat(rover, is(sameStateRover));
    }

    @Test
    void shouldBeLookingToEast() {
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
