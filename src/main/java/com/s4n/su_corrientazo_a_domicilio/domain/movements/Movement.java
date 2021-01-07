package com.s4n.su_corrientazo_a_domicilio.domain.movements;

import com.s4n.su_corrientazo_a_domicilio.domain.positioning.Position;

public interface Movement {

    Position apply(Position position);
}
