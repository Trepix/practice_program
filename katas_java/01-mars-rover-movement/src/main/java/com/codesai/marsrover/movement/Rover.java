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
                if (ROTATE_LEFT.equals(commands[0]) && cardinalDirection.equals(CardinalDirection.NORTH)) {
                    cardinalDirection = CardinalDirection.WEST;
                }
                else if (ROTATE_LEFT.equals(commands[0]) && cardinalDirection.equals(CardinalDirection.EAST)) {
                    cardinalDirection = CardinalDirection.NORTH;
                }
                else if (ROTATE_LEFT.equals(commands[0])) {
                    cardinalDirection = CardinalDirection.EAST;
                }
            }
            else {
                if (cardinalDirection.equals(CardinalDirection.EAST)) {
                    cardinalDirection = CardinalDirection.SOUTH;
                }
                else if (cardinalDirection.equals(CardinalDirection.NORTH)) {
                    cardinalDirection = CardinalDirection.EAST;
                }
                else cardinalDirection = CardinalDirection.WEST;
            }

        }
    }
}
