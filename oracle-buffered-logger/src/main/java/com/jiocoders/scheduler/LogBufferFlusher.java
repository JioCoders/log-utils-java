package com.jiocoders.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author - Jiocoders
 * @project - oracle-buffered-logger-app
 * @dateTime - 26-Jul-2026 17:04
 * <p>
 * Copyright (c) 2026. All rights reserved.
 */
@Component
public class LogBufferFlusher {

    @Scheduled(fixedRateString = "${event.flush.interval-ms:50000}")
    public void flushLogsToDatabase() {
        System.out.println("Scheduler is running...");
        // Triggering batch log flush to Oracle DB...
        // Nothing doing here...
    }

}