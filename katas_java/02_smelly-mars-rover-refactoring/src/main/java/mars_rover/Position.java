package mars_rover;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class Position {
    private final int x;
    private final int y;

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }
}
