package mars_rover;

import java.util.List;

public interface CommunicationInterpreter {
    NavigationCommand translate(String command);
    List<String> split(String commandSequence);
    List<NavigationCommand> translateMultiple(String commandSequence);
}
