package com.zhanghao.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author zhanghao
 * @data 2021/06/17
 */
@Configuration
@EnableConfigurationProperties(TaskThreadPoolConfig.class)
public class MyThreadPoolTaskExecutor {

    /*
     * 自定义异步线程池
     */
    @Bean
    public ThreadPoolTaskExecutor taskExecutor(TaskThreadPoolConfig properties) {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(properties.getCorePoolSize());
        pool.setMaxPoolSize(properties.getMaxPoolSize());
        pool.setQueueCapacity(properties.getQueueCapacity());
        pool.setKeepAliveSeconds(properties.getKeepAliveSeconds());
        pool.setThreadNamePrefix(properties.getThreadNamePrefix());
        return pool;
    }
}
