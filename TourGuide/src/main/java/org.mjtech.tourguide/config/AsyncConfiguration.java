package org.mjtech.tourguide.config;

import java.util.concurrent.Executor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


/**
 * AsyncConfiguration. class that configure
 * threadPool task executor
 */

@EnableAsync
@Configuration
public class AsyncConfiguration implements AsyncConfigurer {

  /*@Override
  public Executor getAsyncExecutor() {

    return Executors.newFixedThreadPool(1000);
  }*/

  /**
   * getAsyncExecutor. Method that initialize
   * ThreadPoolTaskExecutor
   *
   * @return executor
   */
  @Override
  public Executor getAsyncExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(100);
    executor.setMaxPoolSize(100);
    executor.setQueueCapacity(1000);
    executor.initialize();
    return executor;
  }

}