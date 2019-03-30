package mars_rover;

import java.util.List;

public interface CommunicationInterpreter {
    List<NavigationCommand> translateSequence(String commandSequence);
}
