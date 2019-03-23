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

        @Override
        Position moveForward(Position position) {
            return position.moveOnYAxis(1);
        }

        @Override
        Position moveBackward(Position position) {
            return position.moveOnYAxis(-1);
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

        @Override
        Position moveForward(Position position) {
            return position.moveOnXAxis(1);
        }

        @Override
        Position moveBackward(Position position) {
            return position.moveOnXAxis(-1);
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

        @Override
        Position moveForward(Position position) {
            return position.moveOnYAxis(-1);
        }

        @Override
        Position moveBackward(Position position) {
            return position.moveOnYAxis(1);
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

        @Override
        Position moveForward(Position position) {
            return null;
        }

        @Override
        Position moveBackward(Position position) {
            return null;
        }
    };


    abstract CardinalDirection rotateRight();

    abstract CardinalDirection rotateLeft();

    abstract Position moveForward(Position position);

    abstract Position moveBackward(Position position);
}
