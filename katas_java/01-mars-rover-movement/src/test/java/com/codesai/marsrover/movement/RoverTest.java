package com.codesai.marsrover.movement;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codesai.marsrover.movement.CardinalDirection.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class RoverTest {

    @Test
    @DisplayName("Given empty string commands should not move")
    void shouldNotMoveRover() {
        Rover rover = createRoverLookingTo(NORTH);

        rover.process();

        Rover sameStateRover = createRoverLookingTo(NORTH);
        assertThat(rover, is(sameStateRover));
    }

    @MethodSource("turnRightCommandProvider")
    @DisplayName("Given any position when turn right command ")
    @ParameterizedTest(name = "and was looking to {0} then should be looking to {1}")
    void shouldReturnCardinalDirectionAfterRotatePlus90Degrees(CardinalDirection initial, CardinalDirection afterCommand) {
        Rover rover = createRoverLookingTo(initial);
        rover.process("r");
        assertThat(rover, is(createRoverLookingTo(afterCommand)));
    }

    private static Stream<Arguments> turnRightCommandProvider() {
        return Stream.of(
                arguments(NORTH, EAST),
                arguments(EAST, SOUTH),
                arguments(SOUTH, WEST),
                arguments(WEST, NORTH)
        );
    }

    @MethodSource("turnLeftCommandProvider")
    @DisplayName("Given any position when turn left command ")
    @ParameterizedTest(name = "and was looking to {0} then should be looking to {1}")
    void shouldReturnCardinalDirectionAfterRotateMinus90Degrees(CardinalDirection initial, CardinalDirection afterCommand) {
        Rover rover = createRoverLookingTo(initial);
        rover.process("l");
        assertThat(rover, is(createRoverLookingTo(afterCommand)));
    }

    private static Stream<Arguments> turnLeftCommandProvider() {
        return Stream.of(
                arguments(NORTH, WEST),
                arguments(WEST, SOUTH),
                arguments(SOUTH, EAST),
                arguments(EAST, NORTH)
        );
    }

    @Test
    void shouldMoveOnePositiveUnitOnYAxis() {
        Rover rover = createRoverLookingTo(NORTH);

        rover.process("f");

        Rover movedRover = new Rover(NORTH, new Position(0,1));
        assertThat(rover, is(movedRover));
    }

    private static Rover createRoverLookingTo(CardinalDirection cardinalDirection) {
        return new Rover(cardinalDirection, originPosition());
    }

    private static Position originPosition() {
        return new Position(0, 0);
    }
}
