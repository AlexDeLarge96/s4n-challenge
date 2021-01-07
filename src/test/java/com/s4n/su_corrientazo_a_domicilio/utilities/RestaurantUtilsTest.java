package com.s4n.su_corrientazo_a_domicilio.utilities;

import com.s4n.su_corrientazo_a_domicilio.domain.Drone;
import com.s4n.su_corrientazo_a_domicilio.domain.movements.Movement;
import com.s4n.su_corrientazo_a_domicilio.domain.movements.NoMovement;
import com.s4n.su_corrientazo_a_domicilio.domain.tasks.Delivery;
import com.s4n.su_corrientazo_a_domicilio.domain.tasks.EmptyTask;
import com.s4n.su_corrientazo_a_domicilio.domain.tasks.Task;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.s4n.su_corrientazo_a_domicilio.domain.movements.PositionChange.UP;
import static com.s4n.su_corrientazo_a_domicilio.domain.movements.RotationChange.LEFT;
import static com.s4n.su_corrientazo_a_domicilio.domain.movements.RotationChange.RIGHT;
import static com.s4n.su_corrientazo_a_domicilio.utilities.RestaurantUtils.*;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("SpellCheckingInspection")
class RestaurantUtilsTest {

    @Test
    void shouldInitializeDrones() {
        List<Drone> drones = initializeDrones(1, 2, 3);

        assertEquals(1, drones.size());
        assertEquals("01", drones.get(0).getId());
        assertEquals(2, drones.get(0).getCapacity());
        assertEquals(3, drones.get(0).getRange());
        assertEquals("(0,0) dirección Norte", drones.get(0).getPosition().toString());
        assertTrue(drones.get(0).getTask() instanceof EmptyTask);
        assertTrue(drones.get(0).getTaskResult().getDeliveriesResults().isEmpty());
        assertTrue(drones.get(0).getTaskResult().toString().isEmpty());
    }

    @Test
    void shouldLoadDroneTasks() {
        List<Task> tasks = RestaurantUtils.loadDroneTask(3);

        assertNotNull(tasks);
    }

    @Test
    void shouldCheckMaxDeliveryListSize() {
        List<Delivery> deliveries = Arrays.asList(new Delivery(emptyList()), new Delivery(emptyList()));
        List<Delivery> boundedDeliveryList = checkMaximumDeliveries(1, deliveries);

        assertEquals(1, boundedDeliveryList.size());
    }

    @Test
    void shouldReturnDeliveriesList() {
        List<Delivery> deliveries = getDeliveries(Arrays.asList("A", "D"));

        assertEquals(2, deliveries.size());
        assertEquals(1, deliveries.get(0).getMovements().size());
        assertEquals(UP, deliveries.get(0).getMovements().get(0));
        assertEquals(1, deliveries.get(1).getMovements().size());
        assertEquals(RIGHT, deliveries.get(1).getMovements().get(0));
    }

    @Test
    void shouldReturnMovementList() {
        List<Movement> movements = getMovementList("ADIB");

        assertEquals(3, movements.size());
        assertEquals(UP, movements.get(0));
        assertEquals(RIGHT, movements.get(1));
        assertEquals(LEFT, movements.get(2));
    }

    @Test
    void shouldReturnUpMovement() {
        assertEquals(UP, getMovement('A'));
    }

    @Test
    void shouldReturnRightMovement() {
        assertEquals(RIGHT, getMovement('D'));
    }

    @Test
    void shouldReturnLeftMovement() {
        assertEquals(LEFT, getMovement('I'));
    }

    @Test
    void shouldReturnNoMovement() {
        assertTrue(getMovement('X') instanceof NoMovement);
    }
}