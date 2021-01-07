package com.s4n.su_corrientazo_a_domicilio.domain.movements;

import com.s4n.su_corrientazo_a_domicilio.domain.positioning.Position;
import org.jetbrains.annotations.NotNull;

public class NoMovement implements Movement {

    @Override
    public Position apply(@NotNull Position position) {
        return position;
    }

    @Override
    public String toString() {
        return "";
    }
}
