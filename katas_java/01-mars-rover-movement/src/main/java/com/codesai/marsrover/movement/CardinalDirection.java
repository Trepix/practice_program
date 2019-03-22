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
            return null;
        }

        @Override
        CardinalDirection rotateLeft() {
            return null;
        }
    };


    abstract CardinalDirection rotateRight();
    abstract CardinalDirection rotateLeft();
}
