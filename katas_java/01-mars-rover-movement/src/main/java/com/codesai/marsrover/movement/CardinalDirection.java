package com.codesai.marsrover.movement;

enum CardinalDirection {
    NORTH, EAST, SOUTH, WEST;

    CardinalDirection turnRight90Degrees() {
         return EAST;
    }

    CardinalDirection turnLeft90Degrees() {
        return WEST;
    }

}
