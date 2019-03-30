package mars_rover;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import mars_rover.interpreters.NASACommunicationInterpreter;
import mars_rover.navigation.Coordinates;
import mars_rover.navigation.Direction;

@ToString(exclude = "communicationInterpreter")
@EqualsAndHashCode(exclude = "communicationInterpreter")
public class MarsRover {

    private Navigation navigation;
    private CommunicationInterpreter communicationInterpreter;

    public MarsRover(int x, int y, String direction) {
        this.navigation = new Navigation(new Coordinates(x, y), Direction.parse(direction));
        this.communicationInterpreter = new NASACommunicationInterpreter();
    }

    public void receive(String commandsSequence) {
        for (NavigationCommand command : this.communicationInterpreter.translateSequence(commandsSequence)) {
            navigation = command.perform(navigation);
        }
    }
}
