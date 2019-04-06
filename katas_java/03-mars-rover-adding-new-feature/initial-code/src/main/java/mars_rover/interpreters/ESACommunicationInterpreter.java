package mars_rover.interpreters;

import mars_rover.CommunicationInterpreter;
import mars_rover.NavigationCommand;
import mars_rover.commands.*;

import java.util.List;

import static java.util.Collections.singletonList;

public class ESACommunicationInterpreter implements CommunicationInterpreter {

    private static final int MOVEMENT_DELTA = 1;

    @Override
    public List<NavigationCommand> translateSequence(String commandSequence) {
        return singletonList(translate(commandSequence));
    }

    private NavigationCommand translate(String command) {
        if ("l".equals(command)) {
            return new TurnRightCommand();
        } else if ("f".equals(command)) {
            return new TurnLeftCommand();
        } else if ("b".equals(command)) {
            return new MoveForwardCommand(MOVEMENT_DELTA);
        } else if ("x".equals(command)) {
            return new MoveBackwardCommand(MOVEMENT_DELTA);
        } else {
            return new NoOperationCommand();
        }
    }
}
