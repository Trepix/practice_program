package com.codesai.marsrover.movement;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import static com.codesai.marsrover.movement.CardinalDirection.EAST;
import static com.codesai.marsrover.movement.CardinalDirection.NORTH;

@ToString
@EqualsAndHashCode
class Rover {

    private static final String ROTATE_LEFT = "l";
    private static final String ROTATE_RIGHT = "r";
    private static final String MOVE_FORWARD = "f";
    private static final String MOVE_BACKWARD = "b";

    private CardinalDirection cardinalDirection;
    private Position position;

    Rover(CardinalDirection cardinalDirection, Position position) {
        this.cardinalDirection = cardinalDirection;
        this.position = position;
    }

    void process(String... commands) {
        if (commands.length != 0) {
            if (ROTATE_LEFT.equals(commands[0])) {
                cardinalDirection = cardinalDirection.rotateLeft();
            }
            else if (ROTATE_RIGHT.equals(commands[0])) {
                cardinalDirection = cardinalDirection.rotateRight();
            }
            else if (MOVE_FORWARD.equals(commands[0])) {
                if (cardinalDirection.equals(NORTH)) position = new Position(0, 1);
                else if (cardinalDirection.equals(EAST)) position = new Position(1, 0);
                else position = new Position(0, -1);
            }
            else if (MOVE_BACKWARD.equals(commands[0])){
                if (cardinalDirection.equals(NORTH)) position = new Position(0, -1);
                else position = new Position(-1, 0);

            }
        }
    }

}
