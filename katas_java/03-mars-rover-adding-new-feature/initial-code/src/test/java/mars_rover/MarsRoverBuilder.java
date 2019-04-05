package mars_rover;

import mars_rover.interpreters.NASACommunicationInterpreter;
import mars_rover.navigation.Coordinates;
import mars_rover.navigation.Direction;

class MarsRoverBuilder {

    private int x;
    private int y;
    private String direction;

    private MarsRoverBuilder() {

    }

    static MarsRoverBuilder anyMarsRover() {
        return new MarsRoverBuilder().at(0, 0).facing("N");
    }

    static MarsRoverBuilder aMarsRoverAnywhere() {
        return new MarsRoverBuilder().at(0, 0);
    }


    static MarsRoverBuilder aMarsRover() {
        return new MarsRoverBuilder();
    }

    MarsRoverBuilder at(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    MarsRoverBuilder facing(String direction) {
        this.direction = direction;
        return this;
    }

    MarsRover build() {
        Navigation navigation = new Navigation(new Coordinates(x,y), Direction.parse(direction));
        return new MarsRover(navigation, new NASACommunicationInterpreter());
    }
}
