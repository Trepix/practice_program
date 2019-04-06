package mars_rover.interpreters;

import mars_rover.CommunicationInterpreter;
import mars_rover.NavigationCommand;
import mars_rover.commands.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ESACommunicationInterpreter implements CommunicationInterpreter {

    private static final int MOVEMENT_DELTA = 1;

    @Override
    public List<NavigationCommand> translateSequence(String commandSequence) {
        return split(commandSequence).stream().map(this::translate).collect(Collectors.toList());
    }

    private List<String> split(String commandSequence) {
        return Arrays.asList(commandSequence.split(""));
    }

    private NavigationCommand translate(String command) {
        switch (command) {
            case "l":
                return new TurnRightCommand();
            case "f":
                return new TurnLeftCommand();
            case "b":
                return new MoveForwardCommand(MOVEMENT_DELTA);
            case "x":
                return new MoveBackwardCommand(MOVEMENT_DELTA);
            default:
                return new NoOperationCommand();
        }
    }
}
