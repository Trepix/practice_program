package mars_rover;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import mars_rover.commands.*;
import mars_rover.navigation.Coordinates;
import mars_rover.navigation.Direction;

@ToString
@EqualsAndHashCode
public class MarsRover {

    private static final int MOVEMENT_DELTA = 1;
    private static final NavigationCommand TURN_RIGHT_COMMAND = new TurnRightCommand();
    private static final NavigationCommand TURN_LEFT_COMMAND = new TurnLeftCommand();
    private static final NavigationCommand MOVE_FORWARD_COMMAND = new MoveForwardCommand(MOVEMENT_DELTA);
    private static final NavigationCommand MOVE_BACKWARD_COMMAND = new MoveBackwardCommand(MOVEMENT_DELTA);
    private static final NavigationCommand UNKNOWN_COMMAND = new NoOperationCommand();

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
        navigation = getCommand(command).perform(this.navigation);
    }

    private NavigationCommand getCommand(String command) {
        if (command.equals("r")) {
            return TURN_RIGHT_COMMAND;
        } else if (command.equals("l")) {
            return TURN_LEFT_COMMAND;
        } else if (command.equals("f")) {
            return MOVE_FORWARD_COMMAND;
        } else if (command.equals("b")) {
            return MOVE_BACKWARD_COMMAND;
        }
        return UNKNOWN_COMMAND;
    }
}
