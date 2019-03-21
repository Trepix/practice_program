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
            if (cardinalDirection.equals(CardinalDirection.NORTH)) {
                if (ROTATE_LEFT.equals(commands[0])) cardinalDirection = CardinalDirection.WEST;
                else cardinalDirection = CardinalDirection.EAST;
            }
            else if (cardinalDirection.equals(CardinalDirection.EAST)) {
                if (ROTATE_LEFT.equals(commands[0])) cardinalDirection = CardinalDirection.NORTH;
                else cardinalDirection = CardinalDirection.SOUTH;
            }
            else cardinalDirection = CardinalDirection.WEST;

        }
    }
}
