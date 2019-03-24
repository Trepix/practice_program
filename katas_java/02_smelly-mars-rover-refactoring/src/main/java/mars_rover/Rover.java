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
                if (getDirection().equals("N")) {
                    setDirection("W");
                } else if (getDirection().equals("S")) {
                    setDirection("E");
                } else if (getDirection().equals("W")) {
                    setDirection("S");
                } else {
                    setDirection("N");
                }
            } else if (command.equals("r")) {
                if (getDirection().equals("N")) {
                    setDirection("E");
                } else if (getDirection().equals("S")) {
                    setDirection("W");
                } else if (getDirection().equals("W")) {
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

    private void setDirection(String direction) {
        this.direction = direction;
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
        if (getDirection().equals("N")) {
            moveInYAxis(displacement);
        } else if (getDirection().equals("S")) {
            moveInYAxis(-displacement);
        } else if (getDirection().equals("W")) {
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

    private String getDirection() {
        return direction;
    }
}
