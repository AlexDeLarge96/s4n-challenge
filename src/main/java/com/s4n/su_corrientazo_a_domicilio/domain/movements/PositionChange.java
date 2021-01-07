package com.s4n.su_corrientazo_a_domicilio.domain.movements;

import com.s4n.su_corrientazo_a_domicilio.domain.positioning.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor(access = PRIVATE)
public enum PositionChange implements Movement {
    UP("A", 1);

    private final String type;
    private final int quantity;

    @Override
    public Position apply(@NotNull Position position) {
        switch (position.getDirection().getType() + this.getType()) {
            case "NA":
                return new Position(position.getX(), position.getY() + this.getQuantity(), position.getDirection());
            case "SA":
                return new Position(position.getX(), position.getY() - this.getQuantity(), position.getDirection());
            case "WA":
                return new Position(position.getX() - this.getQuantity(), position.getY(), position.getDirection());
            case "EA":
                return new Position(position.getX() + this.getQuantity(), position.getY(), position.getDirection());
            default:
                return position;
        }
    }

    @Override
    public String toString() {
        return this.type;
    }
}
