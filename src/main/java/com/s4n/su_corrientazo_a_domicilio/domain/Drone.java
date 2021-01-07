package com.s4n.su_corrientazo_a_domicilio.domain;

import com.s4n.su_corrientazo_a_domicilio.domain.movements.Movement;
import com.s4n.su_corrientazo_a_domicilio.domain.positioning.Position;
import com.s4n.su_corrientazo_a_domicilio.domain.tasks.Delivery;
import com.s4n.su_corrientazo_a_domicilio.domain.tasks.EmptyTask;
import com.s4n.su_corrientazo_a_domicilio.domain.tasks.Task;
import com.s4n.su_corrientazo_a_domicilio.exceptions.DroneOutOfTheLimitsException;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

import static com.s4n.su_corrientazo_a_domicilio.utilities.FileUtils.writeFile;
import static java.lang.String.format;
import static java.util.Collections.emptyList;

@Getter
@SuppressWarnings("SpellCheckingInspection")
public class Drone implements Supplier<Drone> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Drone.class);
    private static final String REPORT_TITLE = "== Reporte de Entregas ==";
    private static final String DRONE_REPORT_FORMAT = "drones_reports/out%s.txt";
    private final String id;
    private final int capacity;
    private final int range;
    private Position position;
    private Task task;
    private Result taskResult;

    public Drone(@NotNull String id, int capacity, int range) {
        this.id = id;
        this.capacity = capacity;
        this.range = range;
        this.position = new Position();
        this.task = new EmptyTask();
        this.taskResult = new Result(emptyList());
    }

    public void setTask(@NotNull Task task) {
        this.task = task;
    }

    @Override
    public Drone get() {
        List<String> deliveriesResults = new LinkedList<>();
        deliveriesResults.add(REPORT_TITLE);
        List<Delivery> deliveries = this.task.getDeliveries();
        for (Delivery delivery : deliveries) {
            deliveriesResults.add(makeDelivery(delivery));
        }

        this.taskResult = new Result(deliveriesResults);
        this.task = new EmptyTask();
        this.position = new Position();
        writeDeliveryResult();
        return this;
    }

    public void writeDeliveryResult() {
        List<String> fileContent = this.taskResult.getDeliveriesResults();
        boolean fileWasWritten = writeFile(format(DRONE_REPORT_FORMAT, this.id), fileContent);
        LOGGER.info("Drone {} report was written successfully: {}", this.id, fileWasWritten);
    }

    public String makeDelivery(@NotNull Delivery delivery) {
        try {
            for (Movement movement : delivery.getMovements()) {
                this.move(movement);
            }
            return this.getPosition().toString();
        } catch (DroneOutOfTheLimitsException e) {
            return e.getMessage();
        }

    }

    public void move(@NotNull Movement movement) {
        Position newPosition = movement.apply(this.position);
        if (newPosition.getMagnitude() > this.range) {
            throw new DroneOutOfTheLimitsException("The drone' delivery path is out of the restaurant range.");
        } else {
            this.position = newPosition;
        }
    }

}
