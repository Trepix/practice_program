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
        if (commands.length() != 0) {
            if (ROTATE_LEFT == commands.charAt(0)) {
                cardinalDirection = cardinalDirection.rotateLeft();
            } else if (ROTATE_RIGHT == commands.charAt(0)) {
                cardinalDirection = cardinalDirection.rotateRight();
            } else if (MOVE_FORWARD == commands.charAt(0)) {
                position = cardinalDirection.moveForward(position);
            } else if (MOVE_BACKWARD == commands.charAt(0)) {
                position = cardinalDirection.moveBackward(position);
            }
        }
    }
}
