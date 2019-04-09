package mars_rover.interpreters;

import mars_rover.CommunicationInterpreter;
import mars_rover.NavigationCommand;
import mars_rover.commands.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static mars_rover.MarsRover.MOVEMENT_DELTA;

public class NASACommunicationInterpreter implements CommunicationInterpreter {

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
                return new TurnRightCommand();
            case "l":
                return new TurnLeftCommand();
            case "f":
                return new MoveForwardCommand(MOVEMENT_DELTA);
            case "b":
                return new MoveBackwardCommand(MOVEMENT_DELTA);
            default:
                return new NoOperationCommand();
        }
    }
}
