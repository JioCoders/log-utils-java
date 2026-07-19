package com.example.logger.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
public class BufferedRepository<T> {

    private final JpaRepository<T, UUID> jpaRepository;
    private final List<T> buffer = new ArrayList<>();

    // Batch size threshold before forcing a flush
    private static final int BATCH_SIZE = 50;

    /**
     * Adds an entity to the memory buffer.
     * Flushes to database immediately if the buffer is full.
     */
    public synchronized void save(T entity) {
        buffer.add(entity);
        if (buffer.size() >= BATCH_SIZE) {
            flush();
        }
    }

    /**
     * Scheduled task to flush any remaining entities in the buffer 
     * every 5 seconds (5000 ms), regardless of buffer size.
     */
    @Scheduled(fixedRate = 5000)
    public synchronized void flush() {
        if (!buffer.isEmpty()) {
            log.info("Flushing {} entities to Oracle Database...", buffer.size());
            jpaRepository.saveAll(buffer);
            buffer.clear();
        }
    }
}
