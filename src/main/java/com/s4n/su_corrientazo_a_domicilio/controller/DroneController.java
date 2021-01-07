package com.s4n.su_corrientazo_a_domicilio.controller;

import com.s4n.su_corrientazo_a_domicilio.domain.Drone;
import com.s4n.su_corrientazo_a_domicilio.domain.tasks.Task;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class DroneController {

    private final ExecutorService executorService = newFixedThreadPool(20);
    private final Queue<Task> tasks = new LinkedList<>();
    private final Queue<Drone> drones;

    public DroneController(@NotNull List<Drone> drones) {
        this.drones = new LinkedBlockingQueue<>(drones);
    }

    public void executeTask(@NotNull Task task) {
        Optional<Drone> optionalDrone = Optional.ofNullable(drones.poll());
        if (optionalDrone.isPresent()) {
            Drone availableDrone = optionalDrone.get();
            availableDrone.setTask(task);
            CompletableFuture.supplyAsync(availableDrone, executorService)
                    .thenAcceptAsync(this::executeNextTask, executorService);
        } else {
            tasks.add(task);
        }
    }

    private void executeNextTask(@NotNull Drone drone) {
        drones.add(drone);
        if (!tasks.isEmpty()) {
            executeTask(tasks.poll());
        } else {
            executorService.shutdown();
        }
    }
}
