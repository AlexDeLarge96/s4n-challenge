package com.s4n.su_corrientazo_a_domicilio.domain.movements;

import com.s4n.su_corrientazo_a_domicilio.domain.positioning.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import static com.s4n.su_corrientazo_a_domicilio.domain.positioning.Direction.*;
import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor(access = PRIVATE)
public enum RotationChange implements Movement {
    RIGHT("D"),
    LEFT("I");

    private final String type;

    @Override
    public Position apply(@NotNull Position position) {
        switch (position.getDirection().getType() + this.getType()) {
            case "ND":
            case "SI":
                return new Position(position.getX(), position.getY(), EAST);
            case "ED":
            case "WI":
                return new Position(position.getX(), position.getY(), SOUTH);
            case "SD":
            case "NI":
                return new Position(position.getX(), position.getY(), WEST);
            case "WD":
            case "EI":
                return new Position(position.getX(), position.getY(), NORTH);
            default:
                return position;
        }
    }

    @Override
    public String toString() {
        return this.type;
    }
}
