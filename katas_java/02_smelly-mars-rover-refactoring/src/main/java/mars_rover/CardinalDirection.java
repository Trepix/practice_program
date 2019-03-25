package mars_rover;

public enum CardinalDirection {
    NORTH {
        @Override
        public CardinalDirection rotateLeft() {
            return EAST;
        }
    }, SOUTH {
        @Override
        public CardinalDirection rotateLeft() {
            return WEST;
        }
    }, EAST {
        @Override
        public CardinalDirection rotateLeft() {
            return SOUTH;
        }
    }, WEST {
        @Override
        public CardinalDirection rotateLeft() {
            return NORTH;
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
}
