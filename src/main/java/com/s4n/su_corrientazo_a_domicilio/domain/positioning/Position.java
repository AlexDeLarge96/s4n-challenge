package com.s4n.su_corrientazo_a_domicilio.domain.positioning;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import static com.s4n.su_corrientazo_a_domicilio.domain.positioning.Direction.NORTH;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static java.lang.String.format;

@Getter
@AllArgsConstructor
@SuppressWarnings("SpellCheckingInspection")
public class Position {

    private final int x;
    private final int y;
    @NotNull
    private final Direction direction;

    public Position() {
        this.x = 0;
        this.y = 0;
        this.direction = NORTH;
    }

    public double getMagnitude() {
        return sqrt(pow(this.getX() - 0.0, 2) + pow(this.getY() - 0.0, 2));
    }

    @Override
    public String toString() {
        return format("(%d,%d) direccion %s", x, y, direction.getName());
    }
}
