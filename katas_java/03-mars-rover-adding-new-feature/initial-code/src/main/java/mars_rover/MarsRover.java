package mars_rover;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import mars_rover.commands.TurnRightCommand;
import mars_rover.navigation.Coordinates;
import mars_rover.navigation.Direction;

@ToString
@EqualsAndHashCode
public class MarsRover {

    private static final int MOVEMENT_DELTA = 1;
    private static final NavigationCommand TURN_RIGHT_COMMAND = new TurnRightCommand();

    private Navigation navigation;

    public MarsRover(int x, int y, String direction) {
        this.navigation = new Navigation(new Coordinates(x, y), Direction.parse(direction));
    }

    public void receive(String commandsSequence) {
        for (String command : commandsSequence.split("")) {
            executeCommand(command);
        }
    }

    private void executeCommand(String command) {
        if (command.equals("r")) {
            navigation = TURN_RIGHT_COMMAND.perform(this.navigation);
        } else if (command.equals("l")) {
            navigation = navigation.turnLeft();
        } else if (command.equals("f")) {
            navigation = navigation.moveForward(MOVEMENT_DELTA);
        } else if (command.equals("b")) {
            navigation = navigation.moveBackward(MOVEMENT_DELTA);
        }
    }
}
