package mars_rover;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import mars_rover.navigation.Coordinates;
import mars_rover.navigation.Direction;

@ToString(exclude = "navigation")
@EqualsAndHashCode(exclude = "navigation")
public class MarsRover {

    private static final int MOVEMENT_DELTA = 1;
    private Coordinates coordinates;
    private Direction direction;
    private Navigation navigation;

    public MarsRover(int x, int y, String direction) {
        this.coordinates = new Coordinates(x, y);
        this.direction = Direction.parse(direction);
        this.navigation = new Navigation(this.coordinates, this.direction);
    }

    public void receive(String commandsSequence) {
        for (String command : commandsSequence.split("")) {
            executeCommand(command);
        }
    }

    private void executeCommand(String command) {
        if (command.equals("r")) {
            navigation = navigation.turnRight();
            direction = navigation.getDirection();
        } else if (command.equals("l")) {
            navigation = navigation.turnLeft();
            direction = navigation.getDirection();
        } else if (command.equals("f")) {
            navigation = navigation.moveForward(MOVEMENT_DELTA);
            coordinates = navigation.getCoordinates();
        } else if (command.equals("b")) {
            navigation = navigation.moveBackward(MOVEMENT_DELTA);
            coordinates = navigation.getCoordinates();
        }
    }
}
