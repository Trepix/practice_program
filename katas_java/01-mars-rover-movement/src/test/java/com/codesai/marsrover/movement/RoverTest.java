package com.codesai.marsrover.movement;

import org.junit.jupiter.api.Disabled;
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

        rover.process("");

        Rover sameStateRover = createRoverLookingTo(NORTH);
        assertThat(rover, is(sameStateRover));
    }

    @MethodSource("moveForwardCommandProvider")
    @DisplayName("Given origin position when move forward command ")
    @ParameterizedTest(name = "and was looking to {0} then should be placed at {1}")
    void shouldReturnNewPositionAfterMoveOneUnitInThisCardinalDirection(CardinalDirection cardinalDirection, Position afterCommandPosition) {
        Rover rover = createRoverLookingTo(cardinalDirection);
        rover.process("f");
        assertThat(rover, is(new Rover(cardinalDirection, afterCommandPosition)));
    }

    private static Stream<Arguments> moveForwardCommandProvider() {
        return Stream.of(
                arguments(NORTH, new Position(0, 1)),
                arguments(WEST, new Position(-1, 0)),
                arguments(SOUTH, new Position(0, -1)),
                arguments(EAST, new Position(1, 0))
        );
    }

    @MethodSource("moveBackwardCommandProvider")
    @DisplayName("Given origin position when move forward command ")
    @ParameterizedTest(name = "and was looking to {0} then should be placed at {1}")
    void shouldReturnNewPositionAfterMoveOneUnitInTheOppositeCardinalDirection(CardinalDirection cardinalDirection, Position afterCommandPosition) {
        Rover rover = createRoverLookingTo(cardinalDirection);
        rover.process("b");
        assertThat(rover, is(new Rover(cardinalDirection, afterCommandPosition)));
    }

    private static Stream<Arguments> moveBackwardCommandProvider() {
        return Stream.of(
                arguments(NORTH, new Position(0, -1)),
                arguments(WEST, new Position(1, 0)),
                arguments(SOUTH, new Position(0, 1)),
                arguments(EAST, new Position(-1, 0))
        );
    }

    @Test
    @DisplayName("Given multiple commands should be able to process it and move")
    void shouldProcessMultipleCommandsFromOrigin() {
        Rover rover = createRoverLookingTo(NORTH);

        rover.process("ffrffrb");

        Rover movedRover = new Rover(SOUTH, new Position(2,3));
        assertThat(rover, is(movedRover));
    }

    @Test
    @DisplayName("Given multiple commands when starting from non origin position should be able to process it and move")
    void shouldProcessMultipleCommandsFromNotOrigin() {
        Rover rover = new Rover(EAST, new Position(4,5));

        rover.process("lbrf");

        Rover movedRover = new Rover(EAST, new Position(5,4));
        assertThat(rover, is(movedRover));
    }

    @Test
    @Disabled
    @DisplayName("Given invalid command we don't know what should do")
    void givenInvalidCommandCase() {
        Rover rover = new Rover(EAST, new Position(4,5));

        rover.process("x");
    }

    private static Rover createRoverLookingTo(CardinalDirection cardinalDirection) {
        return new Rover(cardinalDirection, originPosition());
    }

    private static Position originPosition() {
        return new Position(0, 0);
    }
}
