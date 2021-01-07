package com.s4n.su_corrientazo_a_domicilio.config;

import com.s4n.su_corrientazo_a_domicilio.config.wrappers.DronesConfigWrapper;
import com.s4n.su_corrientazo_a_domicilio.config.wrappers.RestaurantConfigWrapper;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.InputStream;
import java.util.Optional;

import static com.s4n.su_corrientazo_a_domicilio.utilities.FileUtils.getResourceAsInputStream;

@Getter
public class RestaurantConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantConfig.class);
    private static final String YAML = "application.yaml";
    private int numberOfDrones = 0;
    private int dronesCapacity = 0;
    private int restaurantRange = 0;

    public RestaurantConfig() {
        Optional<InputStream> properties = getResourceAsInputStream(YAML);
        properties.ifPresent(this::initializeRestaurant);
    }

    private void initializeRestaurant(InputStream properties) {
        try {
            Yaml yaml = new Yaml();
            RestaurantConfigWrapper restaurantConfig = yaml.loadAs(properties, RestaurantConfigWrapper.class);
            DronesConfigWrapper drones = restaurantConfig.getDrones();
            numberOfDrones = drones.getNumber();
            dronesCapacity = drones.getCapacity();
            restaurantRange = restaurantConfig.getRange();
        } catch (YAMLException e) {
            LOGGER.error("Error loading restaurant config: ", e);
        }
    }
}
