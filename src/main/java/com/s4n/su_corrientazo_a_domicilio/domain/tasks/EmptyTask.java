package com.s4n.su_corrientazo_a_domicilio.domain.tasks;

import java.util.List;

import static java.util.Collections.emptyList;

public class EmptyTask implements Task {
    @Override
    public List<Delivery> getDeliveries() {
        return emptyList();
    }
}
