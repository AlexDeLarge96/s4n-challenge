package com.s4n.su_corrientazo_a_domicilio.config;

import org.junit.jupiter.api.Test;

import static java.lang.Integer.MIN_VALUE;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RestaurantConfigTest {

    @Test
    void shouldLoadRestaurantConfig() {
        RestaurantConfig restaurantConfig = new RestaurantConfig();

        assertTrue(restaurantConfig.getRestaurantRange() > MIN_VALUE);
        assertTrue(restaurantConfig.getDronesCapacity() > MIN_VALUE);
        assertTrue(restaurantConfig.getNumberOfDrones() > MIN_VALUE);
    }
}