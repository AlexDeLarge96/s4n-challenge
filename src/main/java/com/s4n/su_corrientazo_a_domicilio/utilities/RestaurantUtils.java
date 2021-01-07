package com.s4n.su_corrientazo_a_domicilio.utilities;

import com.s4n.su_corrientazo_a_domicilio.domain.Drone;
import com.s4n.su_corrientazo_a_domicilio.domain.movements.Movement;
import com.s4n.su_corrientazo_a_domicilio.domain.movements.NoMovement;
import com.s4n.su_corrientazo_a_domicilio.domain.movements.PositionChange;
import com.s4n.su_corrientazo_a_domicilio.domain.movements.RotationChange;
import com.s4n.su_corrientazo_a_domicilio.domain.tasks.Delivery;
import com.s4n.su_corrientazo_a_domicilio.domain.tasks.DeliveryTask;
import com.s4n.su_corrientazo_a_domicilio.domain.tasks.Task;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.s4n.su_corrientazo_a_domicilio.utilities.FileUtils.getFolderFiles;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class RestaurantUtils {

    private static final String DRONES_ORDERS = "drones_orders";
    private static final String DRONE_ID_FORMAT = "%02d";
    private static final String TXT = ".txt";

    private RestaurantUtils() {
    }

    public static List<Drone> initializeDrones(int numberOfDrones, int capacity, int range) {
        return range(1, numberOfDrones + 1)
                .mapToObj(id -> format(DRONE_ID_FORMAT, id))
                .map(id -> new Drone(id, capacity, range))
                .collect(toList());
    }

    public static List<Task> loadDroneTask(int dronesCapacity) {
        return getFolderFiles(DRONES_ORDERS)
                .stream()
                .filter(file -> file.getPath().endsWith(TXT))
                .map(FileUtils::getFileContent)
                .map(RestaurantUtils::getDeliveries)
                .map(deliveries -> checkMaximumDeliveries(dronesCapacity, deliveries))
                .map(DeliveryTask::new)
                .collect(toList());
    }

    public static List<Delivery> checkMaximumDeliveries(int dronesCapacity, @NotNull List<Delivery> deliveries) {
        return deliveries.size() <= dronesCapacity ? deliveries : deliveries.subList(0, dronesCapacity);
    }

    public static List<Delivery> getDeliveries(@NotNull List<String> fileLines) {
        return fileLines
                .stream()
                .map(RestaurantUtils::getMovementList)
                .map(Delivery::new)
                .collect(toList());
    }

    public static List<Movement> getMovementList(@NotNull String movements) {
        return movements
                .chars()
                .mapToObj(character -> (char) character)
                .map(RestaurantUtils::getMovement)
                .filter(movement -> !(movement instanceof NoMovement))
                .collect(toList());
    }

    public static Movement getMovement(char type) {
        switch (type) {
            case 'A':
                return PositionChange.UP;
            case 'D':
                return RotationChange.RIGHT;
            case 'I':
                return RotationChange.LEFT;
            default:
                return new NoMovement();
        }
    }
}
