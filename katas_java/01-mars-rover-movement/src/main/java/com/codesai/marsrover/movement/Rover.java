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

    void process(String commands) {
        for (char command : commands.toCharArray()) {
            if ('r' == command) cardinalDirection = cardinalDirection.turnRight90Degrees();
            if ('l' == command) cardinalDirection = cardinalDirection.turnLeft90Degrees();
            if ('f' == command) position = position.move(cardinalDirection.getDirectionVector());
            if ('b' == command) position = position.move(cardinalDirection.getDirectionVector().changeDirection());
        }
    }
}
