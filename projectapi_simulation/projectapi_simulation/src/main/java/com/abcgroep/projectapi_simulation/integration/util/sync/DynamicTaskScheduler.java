package com.abcgroep.projectapi_simulation.integration.util.sync;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;

@Component
public class DynamicTaskScheduler {
    private static final Logger logger = LoggerFactory.getLogger(DynamicTaskScheduler.class);

    private final TaskScheduler scheduler;
    private final PeriodicDataSync periodicDataSync;

    @Autowired
    public DynamicTaskScheduler(PeriodicDataSync periodicDataSync) {
        this.periodicDataSync = periodicDataSync;
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.initialize();
        this.scheduler = threadPoolTaskScheduler;
    }

    private ScheduledFuture<?> futureTask;

    @PostConstruct
    public void startTask() {
        scheduleTask(Instant.now().plusSeconds(10));
    }

    private void scheduleTask(Instant startTime) {
        /////Convert Instant to Date for TaskScheduler
        Date start = Date.from(startTime);
        futureTask = scheduler.schedule(this::doWork, start);
    }
    private void doWork() {
        Instant start = Instant.now();

        logger.info("Taak start uitvoering: {}", start);
        periodicDataSync.syncData();
        //logger.info("Taak voltooid");

        ////uitvoeringstijd berekenen
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);


        logger.info("Taak voltooid. Duur: {} milliseconden", duration.toMillis());

        Instant nextStart = start.plusSeconds(60).plus(duration);
        scheduleTask(nextStart);
    }
}
