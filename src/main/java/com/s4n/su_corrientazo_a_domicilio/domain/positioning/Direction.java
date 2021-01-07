package com.s4n.su_corrientazo_a_domicilio.domain.positioning;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor(access = PRIVATE)
@SuppressWarnings("SpellCheckingInspection")
public enum Direction {
    NORTH("N", "Norte"),
    SOUTH("S", "Sur"),
    WEST("W", "Occidente"),
    EAST("E", "Oriente");

    private final String type;
    private final String name;
}
