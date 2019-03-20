package com.codesai.marsrover.movement;

class RoverCreationUtils {
    static Rover createRoverLookingTo(CardinalDirection cardinalDirection) {
        return new Rover(cardinalDirection, originPosition());
    }

    private static Position originPosition() {
        return new Position(0, 0);
    }
}
