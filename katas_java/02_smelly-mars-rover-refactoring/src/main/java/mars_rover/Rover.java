package mars_rover;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode(exclude = "position")
public class Rover {

    private Position position;
    private String direction;
    private int y;
    private int x;

    public Rover(int x, int y, String direction) {
        this.direction = direction;
        setPosition(x, y);
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

                // Displace Rover
                int displacement1 = -1;

                if (command.equals("f")) {
                    displacement1 = 1;
                }
                int displacement = displacement1;

                if (direction.equals("N")) {
                    setPosition(getX(), getY() + displacement);
                } else if (direction.equals("S")) {
                    setPosition(getX(), getY() - displacement);
                } else if (direction.equals("W")) {
                    setPosition(getX() - displacement, getY());
                } else {
                    setPosition(getX() + displacement, getY());
                }
            }
        }
    }

    private int getY() {
        return y;
    }

    private int getX() {
        return x;
    }

    private void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
