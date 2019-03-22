package com.codesai.marsrover.movement;

enum CardinalDirection {
    NORTH {
        @Override
        CardinalDirection rotateRight() {
            return EAST;
        }

        @Override
        CardinalDirection rotateLeft() {
            return WEST;
        }
    },
    EAST {
        @Override
        CardinalDirection rotateRight() {
            return SOUTH;
        }

        @Override
        CardinalDirection rotateLeft() {
            return NORTH;
        }
    },
    SOUTH {
        @Override
        CardinalDirection rotateRight() {
            return WEST;
        }

        @Override
        CardinalDirection rotateLeft() {
            return EAST;
        }
    },
    WEST {
        @Override
        CardinalDirection rotateRight() {
            return NORTH;
        }

        @Override
        CardinalDirection rotateLeft() {
            return SOUTH;
        }
    };


    abstract CardinalDirection rotateRight();

    abstract CardinalDirection rotateLeft();
}
