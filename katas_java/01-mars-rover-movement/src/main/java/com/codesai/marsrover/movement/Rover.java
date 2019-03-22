package com.codesai.marsrover.movement;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
class Rover {

    private static final String ROTATE_LEFT = "l";
    private CardinalDirection cardinalDirection;
    private Position position;

    Rover(CardinalDirection cardinalDirection, Position position) {
        this.cardinalDirection = cardinalDirection;
        this.position = position;
    }

    void process(String... commands) {
        if (commands.length != 0) {
            if (ROTATE_LEFT.equals(commands[0])) {
                rotateToLeft();
            }
            else {
                rotateToRight();
            }
        }
    }

    private void rotateToRight() {
        cardinalDirection = cardinalDirection.rotateRight();
    }

    private void rotateToLeft() {
        cardinalDirection = cardinalDirection.rotateLeft();
    }

}
