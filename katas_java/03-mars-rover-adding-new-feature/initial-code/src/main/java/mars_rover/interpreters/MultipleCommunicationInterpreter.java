package mars_rover.interpreters;

import mars_rover.CommunicationInterpreter;
import mars_rover.NavigationCommand;
import mars_rover.commands.NoOperationCommand;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;


public class MultipleCommunicationInterpreter implements CommunicationInterpreter {

    private CommunicationInterpreter currentInterpreter = new NullCommunicationInterpreter();

    @Override
    public List<NavigationCommand> translateSequence(String commandSequence) {
        return split(commandSequence)
                .stream()
                .map(this::translate)
                .flatMap(Collection::stream)
                .collect(toList());
    }

    private List<String> split(String commandSequence) {
        return Arrays.asList(commandSequence.split(""));
    }

    private List<NavigationCommand> translate(String command) {
        switch (command) {
            case "z":
                currentInterpreter = new NASACommunicationInterpreter();
                return singletonList(new NoOperationCommand());
            case "k":
                currentInterpreter = new ESACommunicationInterpreter();
                return singletonList(new NoOperationCommand());
            default:
                return currentInterpreter.translateSequence(command);
        }
    }

    private static class NullCommunicationInterpreter implements CommunicationInterpreter {
        @Override
        public List<NavigationCommand> translateSequence(String commandSequence) {
            return singletonList(new NoOperationCommand());
        }
    }
}
