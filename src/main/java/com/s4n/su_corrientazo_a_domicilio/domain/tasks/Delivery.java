package com.s4n.su_corrientazo_a_domicilio.domain.tasks;

import com.s4n.su_corrientazo_a_domicilio.domain.movements.Movement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Getter
@AllArgsConstructor
public class Delivery {

    @NotNull
    private final List<Movement> movements;
}
