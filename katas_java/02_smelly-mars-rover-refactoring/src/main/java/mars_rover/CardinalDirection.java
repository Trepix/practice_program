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
    }, SOUTH {
        @Override
        public CardinalDirection rotateLeft() {
            return WEST;
        }

        @Override
        public CardinalDirection rotateRight() {
            return EAST;
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
    }, WEST {
        @Override
        public CardinalDirection rotateLeft() {
            return NORTH;
        }

        @Override
        public CardinalDirection rotateRight() {
            return SOUTH;
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
}
