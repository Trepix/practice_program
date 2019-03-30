package mars_rover;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import mars_rover.interpreters.NASACommunicationInterpreter;
import mars_rover.navigation.Coordinates;
import mars_rover.navigation.Direction;

@ToString
@EqualsAndHashCode
public class MarsRover {

    private Navigation navigation;

    public MarsRover(int x, int y, String direction) {
        this.navigation = new Navigation(new Coordinates(x, y), Direction.parse(direction));
    }

    public void receive(String commandsSequence) {
        for (String command : commandsSequence.split("")) {
            executeCommand(command);
        }
    }

    private void executeCommand(String command) {
        navigation = getCommand(command).perform(this.navigation);
    }

    private NavigationCommand getCommand(String command) {
        return new NASACommunicationInterpreter().translate(command);
    }
}
