package com.jiocoders.config;

import com.jiocoders.utils.AppConstant;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@Getter
public class AppConfig {

    @Value("${event.threadPool.size.batch:800}")
    private int batchSize;

    @Value("${event.threadPool.size.core:5}")
    private int corePoolSize;

    @Value("${event.threadPool.size.max:10}")
    private int maxPoolSize;

    @Bean(name= AppConstant.BUFFERED_TASK_EXECUTOR)
    public TaskExecutor bufferedTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix(AppConstant.SCHEDULER_PREFIX);
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(batchSize);
        executor.initialize();
        return executor;
    }

}
