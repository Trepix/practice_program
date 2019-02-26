package com.codesai.marsrover.movement;

enum CardinalDirection {
    NORTH, EAST, SOUTH, WEST;

    CardinalDirection turnRight90Degrees() {
        switch (this){
            case NORTH:
                return EAST;
            case EAST:
                return SOUTH;
            case SOUTH:
                return WEST;
            case WEST:
                return NORTH;
        }
        throw new IllegalStateException();
    }

    CardinalDirection turnLeft90Degrees() {
        return WEST;
    }

}
