package mars_rover;

import mars_rover.interpreters.ESACommunicationInterpreter;
import mars_rover.interpreters.NASACommunicationInterpreter;
import mars_rover.navigation.Coordinates;
import mars_rover.navigation.Direction;

class MarsRoverBuilder {

    private int x;
    private int y;
    private String direction;
    private CommunicationInterpreter communicationInterpreter;

    private MarsRoverBuilder() {

    }

    static MarsRoverBuilder anyNASAMarsRover() {
        return new MarsRoverBuilder()
                .at(0, 0)
                .facing("N")
                .withNASACommunicationInterpreter();
    }

    static MarsRoverBuilder anyESAMarsRover() {
        return new MarsRoverBuilder()
                .at(0, 0)
                .facing("N")
                .withESACommunicationInterpreter();
    }

    static MarsRoverBuilder aNASAMarsRoverAnywhere() {
        return new MarsRoverBuilder().at(0, 0).withNASACommunicationInterpreter();
    }

    static MarsRoverBuilder anESAMarsRoverAnywhere() {
        return new MarsRoverBuilder().at(0, 0).withESACommunicationInterpreter();
    }

    static MarsRoverBuilder aNASAMarsRover() {
        return new MarsRoverBuilder().withNASACommunicationInterpreter();
    }

    static MarsRoverBuilder anESAMarsRover() {
        return new MarsRoverBuilder().withESACommunicationInterpreter();
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

    private MarsRoverBuilder withNASACommunicationInterpreter() {
        this.communicationInterpreter = new NASACommunicationInterpreter();
        return this;
    }

    private MarsRoverBuilder withESACommunicationInterpreter() {
        this.communicationInterpreter = new ESACommunicationInterpreter();
        return this;
    }

    MarsRover build() {
        Navigation navigation = new Navigation(new Coordinates(x,y), Direction.parse(direction));
        return new MarsRover(navigation, communicationInterpreter);
    }
}
