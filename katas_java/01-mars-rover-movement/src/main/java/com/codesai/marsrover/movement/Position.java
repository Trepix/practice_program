package com.codesai.marsrover.movement;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
class Position {
    private final int x;
    private final int y;

    Position moveOnXAxis(int units) {
        return new Position(x + units, y);
    }

    Position moveOnYAxis(int units) {
        return new Position(x, y + units);
    }
}
