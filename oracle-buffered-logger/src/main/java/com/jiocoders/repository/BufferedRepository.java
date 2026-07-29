package com.jiocoders.repository;

import com.jiocoders.config.AppConfig;
import com.jiocoders.entity.AuditDateEntity;
import com.jiocoders.utils.AppConstant;
import com.jiocoders.utils.PersistentEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Component
public class BufferedRepository {

    private static final Logger log = LoggerFactory.getLogger(BufferedRepository.class);
    private final Map<Class<?>, BlockingQueue<PersistentEvent<?>>> bufferEventMap = new ConcurrentHashMap<>();
    private final Map<Class<?>, JpaRepository<?, UUID>> repositoryMap = new ConcurrentHashMap<>();

    private final TaskExecutor taskExecutor;
    private final AppConfig appConfig;

    public BufferedRepository(@Qualifier(AppConstant.BUFFERED_TASK_EXECUTOR) TaskExecutor taskExecutor, AppConfig appConfig) {
        this.taskExecutor = taskExecutor;
        this.appConfig = appConfig;
    }

    public <T extends AuditDateEntity> void registerRepository(Class<T> clazz, JpaRepository<T, UUID> repository) {
        bufferEventMap.put(clazz, new LinkedBlockingQueue<>());
        repositoryMap.put(clazz, repository);
        taskExecutor.execute(() -> saveAndFlush(clazz));
    }

    public <T extends AuditDateEntity> void buffer(PersistentEvent<T> event) {
        if(!bufferEventMap.get(event.entityClass()).offer(event)) {
            log.error("Error while adding audit logs, no space error");
        };
    }

    @SuppressWarnings("unchecked")
    private <T extends AuditDateEntity> void saveAndFlush(Class<T> clazz) {
        BlockingQueue<PersistentEvent<?>> queue = bufferEventMap.get(clazz);
        JpaRepository<T, UUID> repository = (JpaRepository<T, UUID>) repositoryMap.get(clazz);
        while (true) {
            try {
                PersistentEvent<?> first = queue.poll(1, TimeUnit.SECONDS);
                if (ObjectUtils.isEmpty(first)) {
                    continue;
                }
                List<PersistentEvent<?>> events = new ArrayList<>();
                events.add(first);

                queue.drainTo(events, appConfig.getBatchSize() - 1);
                List<T> insertEntities = new ArrayList<>();
                for (PersistentEvent<?> event : events) {
                    PersistentEvent<T> bEvent = (PersistentEvent<T>) event;
                    insertEntities.add(bEvent.entity());
                }
                if (!insertEntities.isEmpty()) {
                    try {
                        repository.saveAll(insertEntities);
                    } catch (Exception e) {
                        log.error("Failed to save audit log for {}, Error: {}", clazz, e.getMessage());
                    }
                }
            } catch (InterruptedException e) {
                log.error("Interrupted error in audit log data saveAndFlush for {}, Error :{}", clazz, e.getMessage());
            } catch (Exception e) {
                log.error("Error in audit log data saveAndFlush for {}, Error :{}", clazz, e.getMessage());
            }
        }
    }
}
