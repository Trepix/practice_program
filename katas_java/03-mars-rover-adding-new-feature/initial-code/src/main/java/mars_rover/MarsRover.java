package mars_rover;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@ToString(exclude = "communicationInterpreter")
@EqualsAndHashCode(exclude = "communicationInterpreter")
public class MarsRover {

    public static final int MOVEMENT_DELTA = 1;

    private Navigation navigation;
    private CommunicationInterpreter communicationInterpreter;

    MarsRover(Navigation navigation, CommunicationInterpreter communicationInterpreter) {
        this.navigation = navigation;
        this.communicationInterpreter = communicationInterpreter;
    }

    void receive(String commandsSequence) {
        for (NavigationCommand command : translateSequence(commandsSequence)) {
            navigation = command.execute(navigation);
        }
    }

    private List<NavigationCommand> translateSequence(String commandsSequence) {
        return this.communicationInterpreter.translateSequence(commandsSequence);
    }
}
