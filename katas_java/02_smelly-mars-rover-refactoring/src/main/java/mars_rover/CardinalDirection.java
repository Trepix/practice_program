package mars_rover;

public enum CardinalDirection {
    NORTH {
        @Override
        public CardinalDirection rotateLeft() {
            return EAST;
        }

        @Override
        public CardinalDirection rotateRight() {
            return WEST;
        }

        @Override
        public Position move(Position position, int displacement) {
            return moveInYAxis(position, displacement);
        }
    }, SOUTH {
        @Override
        public CardinalDirection rotateLeft() {
            return WEST;
        }

        @Override
        public CardinalDirection rotateRight() {
            return EAST;
        }

        @Override
        public Position move(Position position, int displacement) {
            return moveInYAxis(position, -displacement);
        }
    }, EAST {
        @Override
        public CardinalDirection rotateLeft() {
            return SOUTH;
        }

        @Override
        public CardinalDirection rotateRight() {
            return NORTH;
        }

        @Override
        public Position move(Position position, int displacement) {
            return moveInXAxis(position, displacement);
        }
    }, WEST {
        @Override
        public CardinalDirection rotateLeft() {
            return NORTH;
        }

        @Override
        public CardinalDirection rotateRight() {
            return SOUTH;
        }

        @Override
        public Position move(Position position, int displacement) {
            return moveInXAxis(position, -displacement);
        }
    };

    public static CardinalDirection from(String directionEncoding) {
        if ("N".equals(directionEncoding)) return NORTH;
        if ("W".equals(directionEncoding)) return WEST;
        if ("E".equals(directionEncoding)) return EAST;
        if ("S".equals(directionEncoding)) return SOUTH;
        throw new IllegalArgumentException();
    }

    abstract public CardinalDirection rotateLeft();
    abstract public CardinalDirection rotateRight();
    abstract Position move(Position position, int displacement);

    public Position moveForward(Position position, int unitsToMove) {
        return move(position, unitsToMove);
    }

    public Position moveBackward(Position position, int unitsToMove) {
        return move(position, -unitsToMove);
    }

    private static Position moveInYAxis(Position position, int displacement) {
        return position.moveOnYAxis(displacement);
    }

    private static Position moveInXAxis(Position position, int displacement) {
        return position.moveOnXAxis(displacement);
    }
}
