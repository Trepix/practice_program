package com.codesai.marsrover.movement;

import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
enum CardinalDirection {
    NORTH(0), EAST(90), SOUTH(180), WEST(270);

    private final int degrees;

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
