package mars_rover;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import static mars_rover.CardinalDirection.*;

@ToString
@EqualsAndHashCode
public class Rover {

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
        cardinalDirection = cardinalDirection.rotateLeft();
    }

    private void rotateRight() {
        cardinalDirection = cardinalDirection.rotateRight();
    }

    private boolean isLookingToWest() {
        return cardinalDirection.equals(WEST);
    }

    private boolean isLookingToNorth() {
        return cardinalDirection.equals(NORTH);
    }

    private boolean isLookingToSouth() {
        return cardinalDirection.equals(SOUTH);
    }

    private void moveForward() {
        int displacement = 1;
        move(displacement);
    }

    private void moveBackward() {
        int displacement = -1;
        move(displacement);
    }

    private void move(int displacement) {
        if (isLookingToNorth()) {
            this.position = cardinalDirection.move(this.position, displacement);
        } else if (isLookingToSouth()) {
            moveInYAxis(this.position, -displacement);
        } else if (isLookingToWest()) {
            moveInXAxis(this.position, -displacement);
        } else {
            moveInXAxis(this.position, displacement);
        }
    }

    private void moveInYAxis(Position position, int displacement) {
        this.position = position.moveOnYAxis(displacement);
    }

    private void moveInXAxis(Position position, int displacement) {
        this.position = position.moveOnXAxis(displacement);
    }
}
