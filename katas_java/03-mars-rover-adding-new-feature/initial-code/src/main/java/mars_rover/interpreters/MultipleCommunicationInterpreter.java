package mars_rover.interpreters;

import mars_rover.CommunicationInterpreter;
import mars_rover.NavigationCommand;
import mars_rover.commands.NoOperationCommand;

import java.util.List;

import static java.util.Collections.singletonList;

public class MultipleCommunicationInterpreter implements CommunicationInterpreter {

    @Override
    public List<NavigationCommand> translateSequence(String commandSequence) {
        return singletonList(new NoOperationCommand());
    }
}
