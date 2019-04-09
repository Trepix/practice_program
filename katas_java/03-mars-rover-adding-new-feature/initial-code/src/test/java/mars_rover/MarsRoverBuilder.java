package mars_rover;

import mars_rover.interpreters.ESACommunicationInterpreter;
import mars_rover.interpreters.MultipleCommunicationInterpreter;
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
        return anyMarsRover()
                .withNASACommunicationInterpreter();
    }

    static MarsRoverBuilder anyESAMarsRover() {
        return anyMarsRover()
                .withESACommunicationInterpreter();
    }

    static MarsRoverBuilder anyMultiInterpreterMarsRover() {
        return anyMarsRover()
                .withMultipleCommunicationInterpreter();
    }

    private static MarsRoverBuilder anyMarsRover() {
        return aMarsRoverAnywhere().facing("N");
    }

    private static MarsRoverBuilder aMarsRoverAnywhere() {
        return new MarsRoverBuilder().at(0, 0);
    }

    static MarsRoverBuilder aNASAMarsRoverAnywhere() {
        return aMarsRoverAnywhere().withNASACommunicationInterpreter();
    }

    static MarsRoverBuilder anESAMarsRoverAnywhere() {
        return aMarsRoverAnywhere().withESACommunicationInterpreter();
    }

    static MarsRoverBuilder aMultiInterpreterMarsRoverAnywhere() {
        return aMarsRoverAnywhere().withMultipleCommunicationInterpreter();
    }

    static MarsRoverBuilder aNASAMarsRover() {
        return new MarsRoverBuilder().withNASACommunicationInterpreter();
    }

    static MarsRoverBuilder anESAMarsRover() {
        return new MarsRoverBuilder().withESACommunicationInterpreter();
    }

    static MarsRoverBuilder aMultiInterpreterMarsRover() {
        return new MarsRoverBuilder().withMultipleCommunicationInterpreter();
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

    private MarsRoverBuilder withMultipleCommunicationInterpreter() {
        this.communicationInterpreter = new MultipleCommunicationInterpreter();
        return this;
    }

    MarsRover build() {
        Navigation navigation = new Navigation(new Coordinates(x, y), Direction.parse(direction));
        return new MarsRover(navigation, communicationInterpreter);
    }
}
