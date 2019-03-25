package mars_rover;

import javafx.geometry.Pos;

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
            return null;
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
            return null;
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
            return null;
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
            return null;
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
    abstract public Position move(Position position, int displacement);

    private Position moveInYAxis(Position position, int displacement) {
        return position.moveOnYAxis(displacement);
    }

    private Position moveInXAxis(Position position, int displacement) {
        return position.moveOnXAxis(displacement);
    }
}
