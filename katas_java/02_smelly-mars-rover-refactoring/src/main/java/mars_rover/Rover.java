package mars_rover;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode(exclude = "cardinalDirection")
public class Rover {

    private Position position;
    private String direction;
    private CardinalDirection cardinalDirection;

    public Rover(int x, int y, String direction) {
        setDirection(direction);
        this.position = new Position(x, y);
    }

    public void receive(String commandsSequence) {
        for (int i = 0; i < commandsSequence.length(); ++i) {
            String command = commandsSequence.substring(i, i + 1);

            if (command.equals("l")) {
                if (isLookingToNorth()) {
                    setDirection("W");
                } else if (isLookingToSouth()) {
                    setDirection("E");
                } else if (isLookingToWest()) {
                    setDirection("S");
                } else {
                    setDirection("N");
                }
            } else if (command.equals("r")) {
                if (isLookingToNorth()) {
                    setDirection("E");
                } else if (isLookingToSouth()) {
                    setDirection("W");
                } else if (isLookingToWest()) {
                    setDirection("N");
                } else {
                    setDirection("S");
                }
            } else {
                if (command.equals("f")) {
                    moveForward();
                }
                else moveBackward();
            }
        }
    }

    private boolean isLookingToWest() {
        return getDirection().equals("W");
    }

    private boolean isLookingToNorth() {
        return getDirection().equals("N");
    }

    private boolean isLookingToSouth() {
        return getDirection().equals("S");
    }

    private void setDirection(String direction) {
        this.cardinalDirection = CardinalDirection.from(direction);
        this.direction = direction;
    }

    private String getDirection() {
        return direction;
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
