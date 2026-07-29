package com.jiocoders.utils;

import com.jiocoders.entity.AuditDateEntity;

/**
 * @author - Jiocoders
 * @project - oracle-buffered-logger-app
 * @dateTime - 26-Jul-2026 17:04
 * <p>
 * Copyright (c) 2026. All rights reserved.
 */
public record PersistentEvent<T extends AuditDateEntity>(Class<T> entityClass, T entity) { }