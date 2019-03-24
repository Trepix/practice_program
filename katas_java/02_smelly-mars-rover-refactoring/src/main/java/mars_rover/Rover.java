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
        setDirection(CardinalDirection.from(direction));
        this.position = new Position(x, y);
    }

    public void receive(String commandsSequence) {
        for (int i = 0; i < commandsSequence.length(); ++i) {
            String command = commandsSequence.substring(i, i + 1);

            if (command.equals("l")) {
                if (isLookingToNorth()) {
                    setDirection(WEST);
                } else if (isLookingToSouth()) {
                    setDirection(EAST);
                } else if (isLookingToWest()) {
                    setDirection(SOUTH);
                } else {
                    setDirection(NORTH);
                }
            } else if (command.equals("r")) {
                if (isLookingToNorth()) {
                    setDirection(EAST);
                } else if (isLookingToSouth()) {
                    setDirection(WEST);
                } else if (isLookingToWest()) {
                    setDirection(NORTH);
                } else {
                    setDirection(SOUTH);
                }
            } else {
                if (command.equals("f")) {
                    moveForward();
                } else moveBackward();
            }
        }
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

    private void setDirection(CardinalDirection cardinalDirection) {
        this.cardinalDirection = cardinalDirection;
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
            moveInYAxis(displacement);
        } else if (isLookingToSouth()) {
            moveInYAxis(-displacement);
        } else if (isLookingToWest()) {
            moveInXAxis(-displacement);
        } else {
            moveInXAxis(displacement);
        }
    }

    private void moveInYAxis(int displacement) {
        this.position = position.moveOnYAxis(displacement);
    }

    private void moveInXAxis(int displacement) {
        this.position = position.moveOnXAxis(displacement);
    }
}
