package mars_rover;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
class Coordinates {

    private final int x;
    private final int y;

    Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Coordinates incrementX(int delta) {
        return new Coordinates(x + delta, y);
    }

    Coordinates incrementY(int delta) {
        return new Coordinates(x, y + delta);
    }
}
