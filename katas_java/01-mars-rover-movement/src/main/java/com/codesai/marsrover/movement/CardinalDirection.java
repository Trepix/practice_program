package com.codesai.marsrover.movement;

enum CardinalDirection {
    NORTH {
        @Override
        CardinalDirection rotateRight() {
            return null;
        }
    },
    EAST {
        @Override
        CardinalDirection rotateRight() {
            return SOUTH;
        }
    },
    SOUTH {
        @Override
        CardinalDirection rotateRight() {
            return null;
        }
    },
    WEST {
        @Override
        CardinalDirection rotateRight() {
            return null;
        }
    };


    abstract CardinalDirection rotateRight();
}
