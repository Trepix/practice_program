package com.codesai.marsrover.movement;

import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
enum CardinalDirection {
    NORTH(0, new Vector(0,1)),
    EAST(90, new Vector(1, 0)),
    SOUTH(180, new Vector(0, -1)),
    WEST(270, new Vector(-1, 0));

    private final int degrees;
    private final Vector directionVector;

    Vector getDirectionVector() {
        return this.directionVector;
    }

    CardinalDirection turnRight90Degrees() {
        int newDegreesPosition = (degrees+90)%360;
        return fromDegrees(newDegreesPosition);
    }

    CardinalDirection turnLeft90Degrees() {
        int newDegreesPosition = (degrees-90+360)%360;
        return fromDegrees(newDegreesPosition);
    }

    private static CardinalDirection fromDegrees(int degrees) {
        return Stream.of(values())
                .filter(x -> x.degrees == degrees)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
