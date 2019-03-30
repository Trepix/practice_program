package mars_rover.navigation;

public enum Direction {
    North {
        public Direction turnRight() {
            return East;
        }

        public Direction turnLeft() {
            return West;
        }

        public Coordinates moveForward(Coordinates coordinates, int delta) {
            return coordinates.incrementY(delta);
        }

        public Coordinates moveBackwards(Coordinates coordinates, int delta) {
            return coordinates.incrementY(-delta);
        }
    },
    South {
        public Direction turnRight() {
            return West;
        }

        public Direction turnLeft() {
            return East;
        }

        public Coordinates moveForward(Coordinates coordinates, int delta) {
            return coordinates.incrementY(-delta);
        }

        public Coordinates moveBackwards(Coordinates coordinates, int delta) {
            return coordinates.incrementY(delta);
        }
    },
    East {
        public Direction turnRight() {
            return South;
        }

        public Direction turnLeft() {
            return North;
        }

        public Coordinates moveForward(Coordinates coordinates, int delta) {
            return coordinates.incrementX(delta);
        }

        public Coordinates moveBackwards(Coordinates coordinates, int delta) {
            return coordinates.incrementX(-delta);
        }
    },
    West {
        public Direction turnRight() {
            return North;
        }

        public Direction turnLeft() {
            return South;
        }

        public Coordinates moveForward(Coordinates coordinates, int delta) {
            return coordinates.incrementX(-delta);
        }

        public Coordinates moveBackwards(Coordinates coordinates, int delta) {
            return coordinates.incrementX(delta);
        }
    };

    static public Direction parse(String directionAsString) {
        switch (directionAsString) {
            case "N":
                return North;
            case "E":
                return East;
            case "S":
                return South;
            default:
                return West;
        }
    }

    abstract public Direction turnRight();

    abstract public Direction turnLeft();

    abstract public Coordinates moveForward(Coordinates coordinates, int delta);

    abstract public Coordinates moveBackwards(Coordinates coordinates, int delta);
}
