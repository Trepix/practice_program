package com.codesai.marsrover.movement;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
class Rover {

    private CardinalDirection cardinalDirection;
    private Position position;

    Rover(CardinalDirection cardinalDirection, Position position) {
        this.cardinalDirection = cardinalDirection;
        this.position = position;
    }

    void process(String... commands) {
        if (commands.length == 0) return ;
        if ("r".equals(commands[0])) cardinalDirection = cardinalDirection.turnRight90Degrees();
        if ("l".equals(commands[0])) cardinalDirection = cardinalDirection.turnLeft90Degrees();
        if ("f".equals(commands[0])) position = position.movePositivelyOnYAxis(1);
    }
}
