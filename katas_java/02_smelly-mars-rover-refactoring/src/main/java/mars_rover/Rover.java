package mars_rover;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Rover {

    private static final int UNITS_TO_MOVE = 1;

    private Position position;
    private CardinalDirection cardinalDirection;

    public Rover(int x, int y, String direction) {
        this.cardinalDirection = CardinalDirection.from(direction);
        this.position = new Position(x, y);
    }

    public void receive(String commandsSequence) {
        for (int i = 0; i < commandsSequence.length(); ++i) {
            String command = commandsSequence.substring(i, i + 1);

            switch (command) {
                case "l":
                    rotateRight();
                    break;
                case "r":
                    rotateLeft();
                    break;
                case "f":
                    moveForward();
                    break;
                default:
                    moveBackward();
                    break;
            }
        }
    }

    private void rotateLeft() {
        this.cardinalDirection = cardinalDirection.rotateLeft();
    }

    private void rotateRight() {
        this.cardinalDirection = cardinalDirection.rotateRight();
    }

    private void moveForward() {
        this.position = cardinalDirection.moveForward(position, UNITS_TO_MOVE);
    }

    private void moveBackward() {
        this.position = cardinalDirection.moveBackward(position, UNITS_TO_MOVE);
    }
}
