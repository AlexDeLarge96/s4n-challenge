package com.s4n.su_corrientazo_a_domicilio.config.wrappers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantConfigWrapper {

    private int range;
    private DronesConfigWrapper drones;
}
