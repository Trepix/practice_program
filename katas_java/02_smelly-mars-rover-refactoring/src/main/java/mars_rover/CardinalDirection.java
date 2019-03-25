package mars_rover;

public enum CardinalDirection {
    NORTH {
        @Override
        public CardinalDirection rotateLeft() {
            return EAST;
        }
    }, SOUTH, EAST, WEST;

    public static CardinalDirection from(String directionEncoding) {
        if ("N".equals(directionEncoding)) return NORTH;
        if ("W".equals(directionEncoding)) return WEST;
        if ("E".equals(directionEncoding)) return EAST;
        if ("S".equals(directionEncoding)) return SOUTH;
        throw new IllegalArgumentException();
    }

    public CardinalDirection rotateLeft() {
        return null;
    }
}
