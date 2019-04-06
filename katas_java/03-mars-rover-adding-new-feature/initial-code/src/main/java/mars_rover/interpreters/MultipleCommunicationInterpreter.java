package mars_rover.interpreters;

import mars_rover.CommunicationInterpreter;
import mars_rover.NavigationCommand;
import mars_rover.commands.*;

import java.util.*;

import static java.util.Collections.singletonList;


public class MultipleCommunicationInterpreter implements CommunicationInterpreter {

    private CommunicationInterpreter currentInterpreter = null;

    @Override
    public List<NavigationCommand> translateSequence(String commandSequence) {
        List<NavigationCommand> result = new LinkedList<>();
        for (String command : split(commandSequence)){
            result.addAll(translate(command));
        }
        return result;
    }

    private List<String> split(String commandSequence) {
        return Arrays.asList(commandSequence.split(""));
    }

    private List<NavigationCommand> translate(String command) {
        if ("z".equals(command)) {
            currentInterpreter = new NASACommunicationInterpreter();
        } else if ("k".equals(command)) {
            currentInterpreter = new ESACommunicationInterpreter();
        } else if (getInterpreter().isPresent()) {
            return getInterpreter().get().translateSequence(command);
        }
        return singletonList(new NoOperationCommand());
    }

    private Optional<CommunicationInterpreter> getInterpreter() {
        return Optional.ofNullable(currentInterpreter);
    }
}
