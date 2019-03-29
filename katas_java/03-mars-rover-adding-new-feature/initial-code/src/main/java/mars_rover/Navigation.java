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

    Navigation turnRight() {
        return new Navigation(coordinates, direction.turnRight());
    }

    Navigation turnLeft() {
        return new Navigation(coordinates, direction.turnLeft());
    }

    Navigation moveForward(int delta) {
        return new Navigation(direction.moveForward(coordinates, delta), direction);
    }

    Navigation moveBackward(int delta) {
        return new Navigation(direction.moveBackwards(coordinates, delta), direction);
    }
}
