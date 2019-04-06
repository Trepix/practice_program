package mars_rover.interpreters;

import mars_rover.CommunicationInterpreter;
import mars_rover.NavigationCommand;
import mars_rover.commands.*;

import java.util.List;

import static java.util.Collections.singletonList;

public class ESACommunicationInterpreter implements CommunicationInterpreter {

    @Override
    public List<NavigationCommand> translateSequence(String commandSequence) {
        return singletonList(translate(commandSequence));
    }

    private NavigationCommand translate(String command) {
        if ("l".equals(command)) {
            return new TurnRightCommand();
        } else {
            return new NoOperationCommand();
        }
    }
}
