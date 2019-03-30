package mars_rover.commands;

import lombok.RequiredArgsConstructor;
import mars_rover.Navigation;
import mars_rover.NavigationCommand;

@RequiredArgsConstructor
public class MoveForwardCommand implements NavigationCommand {

    private final int delta;

    @Override
    public Navigation execute(Navigation navigation) {
        return navigation.moveForward(this.delta);
    }
}
