package com.s4n.su_corrientazo_a_domicilio.domain.tasks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Getter
@AllArgsConstructor
public class DeliveryTask implements Task {

    @NotNull
    private final List<Delivery> deliveries;
}
