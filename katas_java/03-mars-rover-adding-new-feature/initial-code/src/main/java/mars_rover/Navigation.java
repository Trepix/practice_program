package mars_rover;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import mars_rover.navigation.Coordinates;
import mars_rover.navigation.Direction;

@Getter
@ToString
@EqualsAndHashCode
class Navigation {
    private Coordinates coordinates;
    private Direction direction;

    Navigation(Coordinates coordinates, Direction direction) {
        this.coordinates = coordinates;
        this.direction = direction;
    }
}
