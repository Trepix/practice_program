package mars_rover;

import mars_rover.navigation.Coordinates;

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
        return new MarsRover(new Coordinates(x,y), direction);
    }
}
