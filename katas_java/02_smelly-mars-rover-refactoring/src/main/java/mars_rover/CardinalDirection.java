package mars_rover;

public enum CardinalDirection {
    NORTH, SOUTH, EAST, WEST;

    public static CardinalDirection from(String cardinalDirection) {
        if ("N".equals(cardinalDirection)) return NORTH;
        if ("W".equals(cardinalDirection)) return WEST;
        if ("E".equals(cardinalDirection)) return EAST;
        if ("S".equals(cardinalDirection)) return SOUTH;
        return null;
    }
}
