package com.codesai.marsrover.movement;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
class Rover {

    private static final char TURN_RIGHT = 'r';
    private static final char TURN_LEFT = 'l';
    private static final char MOVE_FORWARD = 'f';
    private static final char MOVE_BACKWARD = 'b';
    
    private CardinalDirection cardinalDirection;
    private Position position;

    Rover(CardinalDirection cardinalDirection, Position position) {
        this.cardinalDirection = cardinalDirection;
        this.position = position;
    }

    void process(String commands) {
        for (char command : commands.toCharArray()) {
            if (TURN_RIGHT == command) cardinalDirection = cardinalDirection.turnRight90Degrees();
            if (TURN_LEFT == command) cardinalDirection = cardinalDirection.turnLeft90Degrees();
            if (MOVE_FORWARD == command) position = position.move(cardinalDirection.getUnitaryVector());
            if (MOVE_BACKWARD == command) position = position.move(cardinalDirection.getUnitaryVector().changeDirection());
        }
    }
}
