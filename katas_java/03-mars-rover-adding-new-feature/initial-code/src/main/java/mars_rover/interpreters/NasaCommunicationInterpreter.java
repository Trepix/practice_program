package mars_rover.interpreters;

import mars_rover.CommunicationInterpreter;
import mars_rover.NavigationCommand;
import mars_rover.commands.*;

import java.util.Arrays;
import java.util.List;

public class NASACommunicationInterpreter implements CommunicationInterpreter {

    private static final int MOVEMENT_DELTA = 1;
    private static final NavigationCommand TURN_RIGHT_COMMAND = new TurnRightCommand();
    private static final NavigationCommand TURN_LEFT_COMMAND = new TurnLeftCommand();
    private static final NavigationCommand MOVE_FORWARD_COMMAND = new MoveForwardCommand(MOVEMENT_DELTA);
    private static final NavigationCommand MOVE_BACKWARD_COMMAND = new MoveBackwardCommand(MOVEMENT_DELTA);
    private static final NavigationCommand UNKNOWN_COMMAND = new NoOperationCommand();

    @Override
    public NavigationCommand translate(String command) {
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

    @Override
    public List<String> split(String commandSequence) {
        return Arrays.asList(commandSequence.split(""));
    }
}
