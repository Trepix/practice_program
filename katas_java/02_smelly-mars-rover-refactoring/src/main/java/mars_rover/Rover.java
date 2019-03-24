package mars_rover;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Rover {

    private Position position;
    private String direction;

    public Rover(int x, int y, String direction) {
        this.direction = direction;
        this.position = new Position(x, y);
    }

    public void receive(String commandsSequence) {
        for (int i = 0; i < commandsSequence.length(); ++i) {
            String command = commandsSequence.substring(i, i + 1);

            if (command.equals("l")) {
                if (direction.equals("N")) {
                    direction = "W";
                } else if (direction.equals("S")) {
                    direction = "E";
                } else if (direction.equals("W")) {
                    direction = "S";
                } else {
                    direction = "N";
                }
            } else if (command.equals("r")) {
                if (direction.equals("N")) {
                    direction = "E";
                } else if (direction.equals("S")) {
                    direction = "W";
                } else if (direction.equals("W")) {
                    direction = "N";
                } else {
                    direction = "S";
                }
            } else {
                if (command.equals("f")) {
                    move(1);
                }
                else move(-1); }
        }
    }

    private void move(int displacement) {
        if (direction.equals("N")) {
            moveInYAxis(displacement);
        } else if (direction.equals("S")) {
            moveInYAxis(-displacement);
        } else if (direction.equals("W")) {
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
