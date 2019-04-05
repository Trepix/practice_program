package mars_rover;

class MarsRoverBuilder {

    private int x;
    private int y;
    private String direction;

    private MarsRoverBuilder() {

    }

    static MarsRoverBuilder aMarsRover(int x, int y, String direction) {
        MarsRoverBuilder builder = new MarsRoverBuilder();
        builder.x = x;
        builder.y = y;
        builder.direction = direction;
        return builder;
    }

    MarsRover build() {
        return new MarsRover(x, y, direction);
    }
}
