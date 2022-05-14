package com.example.redisuniquegenerator.config;

import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadPoolConfig {

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setMaxPoolSize(1);
        threadPoolTaskExecutor.setCorePoolSize(1);
        threadPoolTaskExecutor.setQueueCapacity(1);
        threadPoolTaskExecutor.setRejectedExecutionHandler(new CallerRunsPolicy());
        return threadPoolTaskExecutor;
    }
}
