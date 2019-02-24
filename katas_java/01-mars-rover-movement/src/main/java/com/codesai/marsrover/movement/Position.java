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
}
