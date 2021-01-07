package com.s4n.su_corrientazo_a_domicilio.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Getter
@AllArgsConstructor
public class Result {

    @NotNull
    private final List<String> deliveriesResults;

    @Override
    public String toString() {
        return String.join(" ", deliveriesResults);
    }
}
