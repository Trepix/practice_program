package com.codesai.marsrover.movement;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
class Rover {

    private static final char ROTATE_LEFT = 'l';
    private static final char ROTATE_RIGHT = 'r';
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
            if (ROTATE_LEFT == command) {
                cardinalDirection = cardinalDirection.rotateLeft();
            } else if (ROTATE_RIGHT == command) {
                cardinalDirection = cardinalDirection.rotateRight();
            } else if (MOVE_FORWARD == command) {
                position = cardinalDirection.moveForward(position);
            } else if (MOVE_BACKWARD == command) {
                position = cardinalDirection.moveBackward(position);
            }
        }
    }
}
