package com.s4n.su_corrientazo_a_domicilio;

import com.s4n.su_corrientazo_a_domicilio.config.RestaurantConfig;
import com.s4n.su_corrientazo_a_domicilio.controller.DroneController;
import com.s4n.su_corrientazo_a_domicilio.domain.Drone;
import com.s4n.su_corrientazo_a_domicilio.domain.tasks.Task;

import java.util.List;

import static com.s4n.su_corrientazo_a_domicilio.utilities.RestaurantUtils.initializeDrones;
import static com.s4n.su_corrientazo_a_domicilio.utilities.RestaurantUtils.loadDroneTask;

public class RestaurantApp {

    public static void main(String[] args) {
        RestaurantConfig restaurantConfig = new RestaurantConfig();
        final int dronesRange = restaurantConfig.getRestaurantRange();
        final int dronesCapacity = restaurantConfig.getDronesCapacity();
        final int numberOfDrones = restaurantConfig.getNumberOfDrones();
        List<Drone> drones = initializeDrones(numberOfDrones, dronesCapacity, dronesRange);
        List<Task> tasks = loadDroneTask(dronesCapacity);
        DroneController droneController = new DroneController(drones);
        for (Task task : tasks) {
            droneController.executeTask(task);
        }
    }
}
