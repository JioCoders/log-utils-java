package com.jiocoders.listener;

import com.jiocoders.repository.BufferedRepository;
import com.jiocoders.utils.PersistentEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author - Jiocoders
 * @project - oracle-buffered-logger-app
 * @class - LogEventListener
 * @dateTime - 26-Jul-2026 17:04
 * <p>
 * Copyright (c) 2026. All rights reserved.
 */
@Component
@RequiredArgsConstructor
public class LogEventListener {

    private final BufferedRepository bufferedRepository;

    /**
     * Listens to Log Events published anywhere in the application.
     */
    @EventListener
    public void onEventLog(PersistentEvent<?> event) {
        bufferedRepository.buffer(event);
    }

}
