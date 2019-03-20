package com.codesai.marsrover.movement;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codesai.marsrover.movement.CardinalDirection.*;
import static com.codesai.marsrover.movement.RoverCreationUtils.createRoverLookingTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class RoverDisplacementTests {

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
}
