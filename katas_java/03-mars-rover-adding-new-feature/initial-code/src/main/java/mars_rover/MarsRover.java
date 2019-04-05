package mars_rover;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import mars_rover.interpreters.NASACommunicationInterpreter;
import mars_rover.navigation.Coordinates;
import mars_rover.navigation.Direction;

import java.util.List;

@ToString(exclude = "communicationInterpreter")
@EqualsAndHashCode(exclude = "communicationInterpreter")
class MarsRover {

    private Navigation navigation;
    private CommunicationInterpreter communicationInterpreter;

    MarsRover(Coordinates coordinates, String direction) {
        this.navigation = new Navigation(coordinates, Direction.parse(direction));
        this.communicationInterpreter = new NASACommunicationInterpreter();
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
