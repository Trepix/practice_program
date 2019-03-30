package mars_rover.interpreters;

import mars_rover.CommunicationInterpreter;
import mars_rover.NavigationCommand;
import mars_rover.commands.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NASACommunicationInterpreter implements CommunicationInterpreter {

    private static final int MOVEMENT_DELTA = 1;
    private static final NavigationCommand TURN_RIGHT_COMMAND = new TurnRightCommand();
    private static final NavigationCommand TURN_LEFT_COMMAND = new TurnLeftCommand();
    private static final NavigationCommand MOVE_FORWARD_COMMAND = new MoveForwardCommand(MOVEMENT_DELTA);
    private static final NavigationCommand MOVE_BACKWARD_COMMAND = new MoveBackwardCommand(MOVEMENT_DELTA);
    private static final NavigationCommand UNKNOWN_COMMAND = new NoOperationCommand();

    @Override
    public List<NavigationCommand> translateSequence(String commandSequence) {
        return split(commandSequence).stream().map(this::translate).collect(Collectors.toList());
    }

    private List<String> split(String commandSequence) {
        return Arrays.asList(commandSequence.split(""));
    }

    private NavigationCommand translate(String command) {
        switch (command) {
            case "r":
                return TURN_RIGHT_COMMAND;
            case "l":
                return TURN_LEFT_COMMAND;
            case "f":
                return MOVE_FORWARD_COMMAND;
            case "b":
                return MOVE_BACKWARD_COMMAND;
            default:
                return UNKNOWN_COMMAND;
        }
    }
}
