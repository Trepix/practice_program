package mars_rover.commands;

import mars_rover.Navigation;
import mars_rover.NavigationCommand;

public class TurnLeftCommand implements NavigationCommand {
    @Override
    public Navigation perform(Navigation navigation) {
        return navigation.turnLeft();
    }
}
